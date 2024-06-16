package org.zhenhaochen.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * Short Link Recycle Bin Interface Layer
 */
public interface RecycleBinService {

    /**
     * recycle bin paging query
     */
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
