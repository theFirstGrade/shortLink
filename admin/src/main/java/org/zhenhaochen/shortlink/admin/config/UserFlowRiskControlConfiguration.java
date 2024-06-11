package org.zhenhaochen.shortlink.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Traffic Risk Control Configuration
 */
@Data
@Component
@ConfigurationProperties(prefix = "short-link.flow-limit")
public class UserFlowRiskControlConfiguration {

    /**
     * enable traffic risk control
     */
    private Boolean enable;

    /**
     * traffic risk control time window
     */
    private String timeWindow;

    /**
     * allowed access time in a time window
     */
    private Long maxAccessCount;
}