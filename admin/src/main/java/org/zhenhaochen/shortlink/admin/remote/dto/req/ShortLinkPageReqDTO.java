package org.zhenhaochen.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Short Link Paging Request Param
 */
@Data
public class ShortLinkPageReqDTO extends Page {

    /**
     * gid
     */
    private String gid;

    /**
     * order by tag
     */
    private String orderTag;
}
