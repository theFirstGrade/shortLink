package org.zhenhaochen.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.project.common.convention.result.Result;
import org.zhenhaochen.shortlink.project.common.convention.result.Results;
import org.zhenhaochen.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.zhenhaochen.shortlink.project.service.RecycleBinService;

/**
 * Recycle Bin Management Controller
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    /**
     * move to recycle bin
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * recycle bin paging query by gid list
     */
    @GetMapping("/api/short-link/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return Results.success(recycleBinService.pageShortLink(requestParam));
    }
}

