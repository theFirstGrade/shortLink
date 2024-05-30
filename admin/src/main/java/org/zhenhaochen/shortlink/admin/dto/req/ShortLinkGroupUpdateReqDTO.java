package org.zhenhaochen.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Update Group Params
 */
@Data
public class ShortLinkGroupUpdateReqDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * group name
     */
    private String name;
}
