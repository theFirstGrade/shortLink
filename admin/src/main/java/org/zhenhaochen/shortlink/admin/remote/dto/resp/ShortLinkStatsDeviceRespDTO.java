package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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

