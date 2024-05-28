package org.zhenhaochen.shortlink.admin.common.enums;

import org.zhenhaochen.shortlink.admin.common.convention.errorcode.IErrorCode;

public enum UserErrorCodeEnum implements IErrorCode {

    USER_TOKEN_FAIL("A000200", "token validation failed"),

    USER_NULL("B000200", "user record does not exist"),

    USER_NAME_EXIST("B000201", "user name already exist"),

    USER_EXIST("B000202", "user record already exist"),

    USER_SAVE_ERROR("B000203", "fail to add user record");

    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
