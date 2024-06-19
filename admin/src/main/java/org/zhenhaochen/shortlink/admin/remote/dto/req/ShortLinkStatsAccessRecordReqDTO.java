package org.zhenhaochen.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Paging Query Short Link Monitor Statistic Request Params
 */
@Data
public class ShortLinkStatsAccessRecordReqDTO extends Page {

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
