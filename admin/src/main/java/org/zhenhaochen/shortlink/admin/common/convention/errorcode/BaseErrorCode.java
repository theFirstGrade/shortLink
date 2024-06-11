package org.zhenhaochen.shortlink.admin.common.convention.errorcode;

/**
 * Basic Error Code Definition
 */
public enum BaseErrorCode implements IErrorCode {

    // ========== Top-level macro error code Client Errors ==========
    CLIENT_ERROR("A000001", "Client-side error"),

    // ========== Second-level macro error code User Registration Errors ==========
    USER_REGISTER_ERROR("A000100", "User registration error"),
    USER_NAME_VERIFY_ERROR("A000110", "Username verification failed"),
    USER_NAME_EXIST_ERROR("A000111", "Username already exists"),
    USER_NAME_SENSITIVE_ERROR("A000112", "Username contains sensitive words"),
    USER_NAME_SPECIAL_CHARACTER_ERROR("A000113", "Username contains special characters"),
    PASSWORD_VERIFY_ERROR("A000120", "Password verification failed"),
    PASSWORD_SHORT_ERROR("A000121", "Password is too short"),
    PHONE_VERIFY_ERROR("A000151", "Phone format verification failed"),

    // ========== Second-level macro error code System request missing idempotent Token ==========
    IDEMPOTENT_TOKEN_NULL_ERROR("A000200", "Idempotent Token is null"),
    IDEMPOTENT_TOKEN_DELETE_ERROR("A000201", "Idempotent Token has been used or expired"),

    // ========== Second-level macro error code request too frequently ==========
    FLOW_LIMIT_ERROR("A000300", "request too frequently"),

    // ========== Top-level macro error code System Execution Errors ==========
    SERVICE_ERROR("B000001", "System execution error"),
    // ========== Second-level macro error code System Execution Timeout ==========
    SERVICE_TIMEOUT_ERROR("B000100", "System execution timeout"),

    // ========== Top-level macro error code Third-party Service Errors ==========
    REMOTE_ERROR("C000001", "Error calling third-party service");

    private final String code;

    private final String message;

    BaseErrorCode(String code, String message) {
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