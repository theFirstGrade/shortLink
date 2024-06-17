package org.zhenhaochen.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zhenhaochen.shortlink.admin.common.biz.user.UserContext;
import org.zhenhaochen.shortlink.admin.common.convention.exception.ServerException;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.dao.entity.GroupDO;
import org.zhenhaochen.shortlink.admin.dao.mapper.GroupMapper;
import org.zhenhaochen.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.zhenhaochen.shortlink.admin.service.RecycleBinService;

import java.util.List;

/**
 * URL Recycle Bin Interface Implementation Layer
 */
@Service(value = "recycleBinServiceImplByAdmin")
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;
    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    @Override
    public Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServerException("no group records under this user");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkActualRemoteService.pageRecycleBinShortLink(requestParam);
    }
}
