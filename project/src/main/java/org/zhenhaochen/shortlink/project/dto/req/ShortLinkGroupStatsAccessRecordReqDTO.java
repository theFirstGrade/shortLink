package org.zhenhaochen.shortlink.project.dto.req;

import lombok.Data;
import org.zhenhaochen.shortlink.project.dao.entity.LinkAccessLogsDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Paging Query Group Monitor Statistic Request Params
 */
@Data
public class ShortLinkGroupStatsAccessRecordReqDTO extends Page<LinkAccessLogsDO> {

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
