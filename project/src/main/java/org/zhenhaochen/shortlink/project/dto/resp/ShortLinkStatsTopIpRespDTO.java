package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link Top Ip Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsTopIpRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * ip
     */
    private String ip;
}