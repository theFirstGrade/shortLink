package org.zhenhaochen.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.project.common.convention.result.Result;
import org.zhenhaochen.shortlink.project.common.convention.result.Results;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.zhenhaochen.shortlink.project.service.ShortLinkService;

/**
 * Short Link Controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * create short link
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * short link paging query
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
}