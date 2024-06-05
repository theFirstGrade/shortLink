package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zhenhaochen.shortlink.project.common.database.BaseDO;

/**
 * Log Access Entity
 */
@Data
@TableName("t_link_access_logs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccessLogsDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * full short link
     */
    private String fullShortUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * user information
     */
    private String user;

    /**
     * browser
     */
    private String browser;

    /**
     * operating system
     */
    private String os;

    /**
     * ip
     */
    private String ip;

    /**
     * network
     */
    private String network;

    /**
     * device
     */
    private String device;

    /**
     * country-province-city
     */
    private String locale;
}

