package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link Basic Monitor daily Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsAccessDailyRespDTO {

    /**
     * date
     */
    private String date;

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

