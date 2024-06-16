package org.zhenhaochen.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.common.convention.result.Results;
import org.zhenhaochen.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import org.zhenhaochen.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.zhenhaochen.shortlink.admin.service.RecycleBinService;

/**
 * Recycle Bin Management Controller
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;
    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    /**
     * move to recycle bin
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        shortLinkActualRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * recycle bin paging query by gid list
     */
    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return recycleBinService.pageRecycleBinShortLink(requestParam);
    }

    /**
     * recover short link
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        shortLinkActualRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * remove short link
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        shortLinkActualRemoteService.removeRecycleBin(requestParam);
        return Results.success();
    }

}

