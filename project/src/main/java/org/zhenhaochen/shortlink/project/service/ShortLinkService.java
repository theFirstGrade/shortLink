package org.zhenhaochen.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

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
     * update short link
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * short link paging query
     * @return short link paging result
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * query short link count in a group
     * @return short link count in a group
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    /**
     * redirect short link
     *
     * @param shortUri short link uri
     * @param request  HTTP request
     * @param response HTTP response
     */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);
}
