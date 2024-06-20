package org.zhenhaochen.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhenhaochen.shortlink.admin.common.biz.user.UserContext;
import org.zhenhaochen.shortlink.admin.common.convention.exception.ClientException;
import org.zhenhaochen.shortlink.admin.common.convention.exception.ServerException;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.dao.entity.GroupDO;
import org.zhenhaochen.shortlink.admin.dao.entity.GroupUniqueDO;
import org.zhenhaochen.shortlink.admin.dao.mapper.GroupMapper;
import org.zhenhaochen.shortlink.admin.dao.mapper.GroupUniqueMapper;
import org.zhenhaochen.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.zhenhaochen.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.zhenhaochen.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.zhenhaochen.shortlink.admin.service.GroupService;
import org.zhenhaochen.shortlink.admin.toolkit.RandomGenerator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.zhenhaochen.shortlink.admin.common.constant.RedisCacheConstant.LOCK_GROUP_CREATE_KEY;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    private final RBloomFilter<String> gidRegisterCachePenetrationBloomFilter;
    private final GroupUniqueMapper groupUniqueMapper;
    private final ShortLinkActualRemoteService shortLinkActualRemoteService;
    private final RedissonClient redissonClient;

    @Value("${short-link.group.max-num}")
    private Integer groupMaxNum;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveGroup(String groupName) {
        this.saveGroup(UserContext.getUsername(), groupName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveGroup(String username, String groupName) {
        RLock lock = redissonClient.getLock(String.format(LOCK_GROUP_CREATE_KEY, username));
        lock.lock();
        try {
            LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                    .eq(GroupDO::getUsername, username)
                    .eq(GroupDO::getDelFlag, 0);
            List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
            if (CollUtil.isNotEmpty(groupDOList) && groupDOList.size() == groupMaxNum) {
                throw new ClientException(String.format("exceed maximum group：%d", groupMaxNum));
            }
            int retryCount = 0;
            int maxRetries = 10;
            String gid = null;
            while (retryCount < maxRetries) {
                gid = saveAndReturnUniqueGroupGid();
                if (StrUtil.isNotEmpty(gid)) {
                    GroupDO groupDO = GroupDO.builder()
                            .gid(gid)
                            .sortOrder(0)
                            .username(username)
                            .name(groupName)
                            .build();
                    baseMapper.insert(groupDO);
                    gidRegisterCachePenetrationBloomFilter.add(gid);
                    break;
                }
                retryCount++;
            }
            if (StrUtil.isEmpty(gid)) {
                throw new ServerException("generate group too frequently");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0)
                .orderByDesc(List.of(GroupDO::getSortOrder, GroupDO::getCreateTime));
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkActualRemoteService
                .listGroupShortLinkCount(groupDOList.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOList = BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
        shortLinkGroupRespDTOList.forEach(each -> {
            Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        });
        return shortLinkGroupRespDTOList;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        int affectRows = baseMapper.update(groupDO, updateWrapper);
        if (affectRows == 0) {
            throw new ClientException("the group does not exist");
        }
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        int affectRows = baseMapper.update(groupDO, updateWrapper);
        if (affectRows == 0) {
            throw new ClientException("the group does not exist");
        }
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(each -> {
            GroupDO groupDO = GroupDO.builder()
                    .sortOrder(each.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO, updateWrapper);
        });
    }

    private String saveAndReturnUniqueGroupGid() {
        String gid = RandomGenerator.generateRandom();
        if (!gidRegisterCachePenetrationBloomFilter.contains(gid)) {
            GroupUniqueDO groupUniqueDO = GroupUniqueDO.builder()
                    .gid(gid)
                    .build();
            try {
                groupUniqueMapper.insert(groupUniqueDO);
            } catch (DuplicateKeyException e) {
                return null;
            }
            return gid;
        }
        return null;
    }
}
