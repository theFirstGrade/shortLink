package org.zhenhaochen.shortlink.project.dto.req;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;

import java.util.List;

/**
 * Recycle Bin Paging Request Param
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page<ShortLinkDO> {

    /**
     * gid list
     */
    private List<String> gidList;
}
