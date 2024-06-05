package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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