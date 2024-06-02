package org.zhenhaochen.shortlink.project.dto.req;

import lombok.Data;

/**
 * Recycle Bin Remove Request Object
 */
@Data
public class RecycleBinRemoveReqDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * full short link
     */
    private String fullShortUrl;
}