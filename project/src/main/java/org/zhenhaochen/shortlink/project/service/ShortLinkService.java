package org.zhenhaochen.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

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
}
