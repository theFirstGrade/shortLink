package org.zhenhaochen.shortlink.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Domain White List Configuration
 */
@Data
@Component
@ConfigurationProperties(prefix = "short-link.goto-domain.white-list")
public class DomainWhiteListConfiguration {

    /**
     * if enable domain white list validation
     */
    private Boolean enable;

    /**
     * white list websites' name collection
     */
    private String names;

    /**
     * white list domains' names collection
     */
    private List<String> details;
}
