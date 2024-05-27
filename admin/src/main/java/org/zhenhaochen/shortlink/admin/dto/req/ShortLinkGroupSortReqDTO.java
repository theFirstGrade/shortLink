package org.zhenhaochen.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Sort Group Params
 */
@Data
public class ShortLinkGroupSortReqDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * sort order
     */
    private Integer sortOrder;
}
