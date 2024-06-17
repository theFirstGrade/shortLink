package org.zhenhaochen.shortlink.gateway.config;

import lombok.Data;

import java.util.List;

/**
 * Filter Configuration
 */
@Data
public class Config {

    /**
     * white list
     */
    private List<String> whitePathList;
}