package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Short Link Basic Information Response Object
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkBaseInfoRespDTO {

    /**
     * description
     */
    private String description;

    /**
     * original url
     */
    private String originUrl;

    /**
     * full short link
     */
    private String fullShortUrl;
}