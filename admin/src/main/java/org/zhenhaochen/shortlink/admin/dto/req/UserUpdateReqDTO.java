package org.zhenhaochen.shortlink.admin.dto.req;

import lombok.Data;

@Data
public class UserUpdateReqDTO {

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * real name
     */
    private String realName;

    /**
     * phone number
     */
    private String phone;

    /**
     * mail
     */
    private String mail;
}
