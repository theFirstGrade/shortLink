package org.zhenhaochen.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * User Response Entity
 */
@Data
public class UserRespDTO {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * real_name
     */
    private String realName;

    /**
     * phone
     */
    private String phone;

    /**
     * mail
     */
    private String mail;
}
