package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link Visitor Type Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsUvRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * visitor type
     */
    private String uvType;

    /**
     * ratio
     */
    private Double ratio;
}
