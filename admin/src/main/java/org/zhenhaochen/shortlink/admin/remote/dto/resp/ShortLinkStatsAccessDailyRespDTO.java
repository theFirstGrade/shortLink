package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short Link Basic Monitor daily Statistic Response Params
 */
@Data
public class ShortLinkStatsAccessDailyRespDTO {

    /**
     * date
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/New_York")
    private Date date;

    /**
     * page view
     */
    private Integer pv;

    /**
     * unique view
     */
    private Integer uv;

    /**
     * unique ip
     */
    private Integer uip;
}

