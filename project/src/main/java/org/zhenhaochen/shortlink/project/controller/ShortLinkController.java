package org.zhenhaochen.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zhenhaochen.shortlink.project.common.convention.result.Result;
import org.zhenhaochen.shortlink.project.common.convention.result.Results;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.zhenhaochen.shortlink.project.service.ShortLinkService;

import java.util.List;

/**
 * Short Link Controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * short link redirect
     */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response) {
        shortLinkService.restoreUrl(shortUri, request, response);
    }

    /**
     * create short link
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * batch create short link
     */
    @PostMapping("/api/short-link/v1/create/batch")
    public Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam) {
        return Results.success(shortLinkService.batchCreateShortLink(requestParam));
    }

    /**
     * update short link
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * short link paging query
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * query short link count in a group
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}