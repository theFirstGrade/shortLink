package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link OS Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsOsRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * os
     */
    private String os;

    /**
     * ratio
     */
    private Double ratio;
}

