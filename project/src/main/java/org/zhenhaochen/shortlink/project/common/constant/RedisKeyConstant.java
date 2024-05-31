package org.zhenhaochen.shortlink.project.common.constant;

/**
 * Redis Key Constant
 */
public class RedisKeyConstant {

    /**
     * short link redirect key prefix
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * short link redirect lock key prefix
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";
}