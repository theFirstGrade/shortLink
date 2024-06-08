package org.zhenhaochen.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Paging Query Group Monitor Statistic Request Params
 */
@Data
public class ShortLinkGroupStatsAccessRecordReqDTO extends Page {

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
