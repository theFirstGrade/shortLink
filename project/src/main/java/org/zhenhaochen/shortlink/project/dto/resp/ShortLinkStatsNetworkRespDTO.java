package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link Network Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsNetworkRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * network
     */
    private String network;

    /**
     * ratio
     */
    private Double ratio;
}