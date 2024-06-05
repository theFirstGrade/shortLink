package org.zhenhaochen.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.remote.ShortLinkRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;

/**
 * Short Link Monitor Controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    /**
     * rebuild SpringCloud later
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * get single short link monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/admin/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkRemoteService.oneShortLinkStats(requestParam);
    }
}
