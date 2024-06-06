package org.zhenhaochen.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Paging Query Short Link Monitor Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsAccessRecordRespDTO {

    /**
     * visitor type
     */
    private String uvType;

    /**
     * browser
     */
    private String browser;

    /**
     * os
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
     * region
     */
    private String locale;

    /**
     * cookie
     */
    private String user;

    /**
     * access time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private Date createTime;
}