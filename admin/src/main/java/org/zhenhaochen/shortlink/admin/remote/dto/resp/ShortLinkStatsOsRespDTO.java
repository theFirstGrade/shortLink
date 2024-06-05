package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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

