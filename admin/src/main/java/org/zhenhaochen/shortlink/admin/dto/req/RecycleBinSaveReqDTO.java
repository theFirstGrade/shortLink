package org.zhenhaochen.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Move To Recycle Bin Request Object
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * full short url
     */
    private String fullShortUrl;
}

