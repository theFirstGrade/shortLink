package org.zhenhaochen.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;
import org.zhenhaochen.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * Recycle Bin Management Interface Layer
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * move to recycle bin
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * recycle bin paging query by gid list
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    /**
     * recover short link
     */
    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);

}
