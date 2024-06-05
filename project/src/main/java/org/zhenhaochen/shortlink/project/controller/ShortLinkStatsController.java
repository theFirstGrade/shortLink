package org.zhenhaochen.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.project.common.convention.result.Result;
import org.zhenhaochen.shortlink.project.common.convention.result.Results;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkStatsRespDTO;
import org.zhenhaochen.shortlink.project.service.ShortLinkStatsService;

/**
 * Short Link Monitor Controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    private final ShortLinkStatsService shortLinkStatsService;

    /**
     * get single short link statistic between specified dates
     */
    @GetMapping("/api/short-link/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }
}
