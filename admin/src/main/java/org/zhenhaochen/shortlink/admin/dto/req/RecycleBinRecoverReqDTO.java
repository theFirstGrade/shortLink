package org.zhenhaochen.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Recycle Bin Recycle Object
 */
@Data
public class RecycleBinRecoverReqDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * full short link
     */
    private String fullShortUrl;
}

