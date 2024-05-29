package org.zhenhaochen.shortlink.admin.common.convention.exception;

import org.zhenhaochen.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import org.zhenhaochen.shortlink.admin.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * Server Exception
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

