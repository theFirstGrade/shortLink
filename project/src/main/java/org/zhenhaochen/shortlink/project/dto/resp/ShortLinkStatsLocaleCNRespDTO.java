package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
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

