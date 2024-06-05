package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Browser Monitor Statistic Response Params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsBrowserRespDTO {

    /**
     * count
     */
    private Integer cnt;

    /**
     * browser
     */
    private String browser;

    /**
     * ratio
     */
    private Double ratio;
}

