package org.zhenhaochen.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * Group response Entity
 */
@Data
public class ShortLinkGroupRespDTO {

    /**
     * gid
     */
    private String gid;

    /**
     * group name
     */
    private String name;

    /**
     * order
     */
    private Integer sortOrder;


    /**
     * short link count
     */
    private Integer shortLinkCount;

}
