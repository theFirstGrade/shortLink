package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Short Link Monitor Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsRespDTO {

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

    /**
     * basic daily statistic
     */
    private List<ShortLinkStatsAccessDailyRespDTO> daily;

    /**
     * region statistic
     */
    private List<ShortLinkStatsLocaleCNRespDTO> localeCnStats;

    /**
     * hour statistic
     */
    private List<Integer> hourStats;

    /**
     * high frequency access IP statistic
     */
    private List<ShortLinkStatsTopIpRespDTO> topIpStats;

    /**
     * weekday statistic
     */
    private List<Integer> weekdayStats;

    /**
     * browser statistic
     */
    private List<ShortLinkStatsBrowserRespDTO> browserStats;

    /**
     * os statistic
     */
    private List<ShortLinkStatsOsRespDTO> osStats;

    /**
     * visitor type statistic
     */
    private List<ShortLinkStatsUvRespDTO> uvTypeStats;

    /**
     * device statistic
     */
    private List<ShortLinkStatsDeviceRespDTO> deviceStats;

    /**
     * network statistic
     */
    private List<ShortLinkStatsNetworkRespDTO> networkStats;
}
