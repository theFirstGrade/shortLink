package org.zhenhaochen.shortlink.project.common.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * global return object
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    /**
     * success
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * code
     */
    private String code;

    /**
     * message
     */
    private String message;

    /**
     * responsive data
     */
    private T data;

    /**
     * request ID
     */
    private String requestId;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}