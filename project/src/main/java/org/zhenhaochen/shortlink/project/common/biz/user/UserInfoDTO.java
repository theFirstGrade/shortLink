package org.zhenhaochen.shortlink.project.common.biz.user;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    /**
     * ID
     */
    @JSONField(name = "id")
    private String userId;

    /**
     * username
     */
    private String username;

    /**
     * real name
     */
    private String realName;
}