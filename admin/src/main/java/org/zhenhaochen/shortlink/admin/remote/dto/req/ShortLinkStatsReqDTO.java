package org.zhenhaochen.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * Short Link Monitor Request Params
 */
@Data
public class ShortLinkStatsReqDTO {

    /**
     * full short link
     */
    private String fullShortUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * start date
     */
    private String startDate;

    /**
     * end date
     */
    private String endDate;

    /**
     * enable flag 0: enabled 1: disabled
     */
    private Integer enableStatus;
}
