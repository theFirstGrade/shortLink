package org.zhenhaochen.shortlink.project.common.convention.exception;

import org.zhenhaochen.shortlink.project.common.convention.errorcode.BaseErrorCode;
import org.zhenhaochen.shortlink.project.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * 服务端异常
 */
public class ServerException extends AbstractException {

    public ServerException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServerException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServerException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServerException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}

