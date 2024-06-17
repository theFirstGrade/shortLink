package org.zhenhaochen.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.common.convention.result.Results;
import org.zhenhaochen.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkBatchCreateReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkBaseInfoRespDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkBatchCreateRespDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.zhenhaochen.shortlink.admin.toolkit.EasyExcelWebUtil;

import java.util.List;

/**
 * Short Link Admin Controller
 */
@RestController(value = "shortLinkControllerByAdmin")
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    /**
     * create short link
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkActualRemoteService.createShortLink(requestParam);
    }

    /**
     * batch create short link
     */
    @SneakyThrows
    @PostMapping("/api/short-link/admin/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "batch create short link -- SaaS short link system", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }

    /**
     * update short link
     */
    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkActualRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * short link paging query
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkActualRemoteService.pageShortLink(requestParam);
    }
}
