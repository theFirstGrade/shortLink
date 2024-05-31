package org.zhenhaochen.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Validate Type
 */
@RequiredArgsConstructor
public enum VailDateTypeEnum {

    /**
     * permanent validity period
     */
    PERMANENT(0),

    /**
     * customized validity period
     */
    CUSTOM(1);

    @Getter
    private final int type;
}
