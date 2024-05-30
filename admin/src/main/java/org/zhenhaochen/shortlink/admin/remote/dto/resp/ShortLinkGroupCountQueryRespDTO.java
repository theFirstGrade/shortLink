package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.Data;

/**
 * Short Link Count Query Response Object
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * short link count in a group
     */
    private Integer shortLinkCount;
}

