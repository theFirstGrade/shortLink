package org.zhenhaochen.shortlink.project.dto.biz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Statistic Entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsRecordDTO {

    /**
     * full short link
     */
    private String fullShortUrl;

    /**
     * ip
     */
    private String remoteAddr;

    /**
     * os
     */
    private String os;

    /**
     * browser
     */
    private String browser;

    /**
     * device
     */
    private String device;

    /**
     * network
     */
    private String network;

    /**
     * uv
     */
    private String uv;

    /**
     * uv flag
     */
    private Boolean uvFirstFlag;

    /**
     * uip flag
     */
    private Boolean uipFirstFlag;

    /**
     * message queue unique id
     */
    private String keys;
}
