package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link Device Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsDeviceRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * device
     */
    private String device;

    /**
     * ratio
     */
    private Double ratio;
}

