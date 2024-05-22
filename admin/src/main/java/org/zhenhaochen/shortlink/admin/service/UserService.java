package org.zhenhaochen.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zhenhaochen.shortlink.admin.dao.entity.UserDO;
import org.zhenhaochen.shortlink.admin.dto.req.UserLoginReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.zhenhaochen.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.zhenhaochen.shortlink.admin.dto.resp.UserRespDTO;

/**
 * User Api Layer
 */
public interface UserService extends IService<UserDO> {

    /**
     * get user information by username
     * @return user entity
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * check if the username exists
     * @return return True if exists，else False
     */
    Boolean hasUsername(String username);

    /**
     * register
     *
     * @param requestParam user register params
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * update user record according to username
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * user login
     * @return token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParams);

    /**
     * check if the user has logged in
     * @return login flag
     */
    Boolean checkLogin(String username, String token);
}
