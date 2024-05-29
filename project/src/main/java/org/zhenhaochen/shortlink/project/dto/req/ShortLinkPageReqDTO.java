package org.zhenhaochen.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;

/**
 * Short Link Paging Request Param
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * gid
     */
    private String gid;
}
