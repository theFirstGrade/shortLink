package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Region Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsLocaleCNRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * province
     */
    private String locale;

    /**
     * ratio
     */
    private Double ratio;
}

