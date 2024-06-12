package org.zhenhaochen.shortlink.admin.common.constant;

public class RedisCacheConstant {

    /**
     * user registration distributed lock prefix
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";

    /**
     * group creation distributed lock prefix
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";

    /**
     * user login cache key prefix
     */
    public static final String USER_LOGIN_KEY = "short-link:login:";
}
