package org.zhenhaochen.shortlink.admin.dto.req;

import lombok.Data;

/**
 * User Login Request Params
 */
@Data
public class UserLoginReqDTO {

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;
}