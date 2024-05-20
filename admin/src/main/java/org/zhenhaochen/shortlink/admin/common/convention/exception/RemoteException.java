package org.zhenhaochen.shortlink.admin.common.convention.exception;

import org.zhenhaochen.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import org.zhenhaochen.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * Remote Service Revoke Exception
 */
public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}