package org.zhenhaochen.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.zhenhaochen.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;

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
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * mail
     */
    private String mail;
}
