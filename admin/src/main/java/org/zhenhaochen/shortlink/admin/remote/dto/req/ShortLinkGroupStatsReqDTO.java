package org.zhenhaochen.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * group query short link monitor statistic
 */
@Data
public class ShortLinkGroupStatsReqDTO {

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
}
