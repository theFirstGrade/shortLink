package org.zhenhaochen.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zhenhaochen.shortlink.admin.common.convention.exception.ClientException;
import org.zhenhaochen.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.zhenhaochen.shortlink.admin.dao.entity.UserDO;
import org.zhenhaochen.shortlink.admin.dao.mapper.UserMapper;
import org.zhenhaochen.shortlink.admin.dto.resp.UserRespDTO;
import org.zhenhaochen.shortlink.admin.service.UserService;

/**
 * User Api Implementation Layer
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
