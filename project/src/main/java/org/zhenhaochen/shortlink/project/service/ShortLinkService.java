package org.zhenhaochen.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * Short Link Interface Layer
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * create short link
     * @param requestParam short link creation params
     * @return short link information
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * short link paging query
     * @return short link paging partition result
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
