package org.zhenhaochen.shortlink.project.common.convention.exception;

import lombok.Getter;
import org.springframework.util.StringUtils;
import org.zhenhaochen.shortlink.project.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * Abstract three exception systems: client exception, server exception, remote service revoke exception
 *
 * @see ClientException
 * @see ServerException
 * @see RemoteException
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
