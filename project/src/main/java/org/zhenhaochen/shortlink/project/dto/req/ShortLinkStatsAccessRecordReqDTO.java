package org.zhenhaochen.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.zhenhaochen.shortlink.project.dao.entity.LinkAccessLogsDO;
import lombok.Data;

/**
 * Paging Query Short Link Monitor Statistic Request Params
 */
@Data
public class ShortLinkStatsAccessRecordReqDTO extends Page<LinkAccessLogsDO> {

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
}
