package org.zhenhaochen.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * Recycle Bin Paging Request Param
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {

    /**
     * gid list
     */
    private List<String> gidList;
}
