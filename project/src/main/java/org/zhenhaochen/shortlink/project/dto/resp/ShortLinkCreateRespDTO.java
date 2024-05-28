package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Creation Response Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkCreateRespDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * origin url
     */
    private String originUrl;

    /**
     * full short link
     */
    private String fullShortUrl;
}