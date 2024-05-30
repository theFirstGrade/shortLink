package org.zhenhaochen.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.remote.dto.ShortLinkRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * Short Link Admin Controller
 */
@RestController
public class ShortLinkController {

    /**
     * rebuild SpringCloud later
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * create short link
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * short link paging query
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
