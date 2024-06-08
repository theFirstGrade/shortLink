package org.zhenhaochen.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.remote.ShortLinkRemoteService;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
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

    /**
     * get a group of short links monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/admin/v1/stats/group")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return shortLinkRemoteService.groupShortLinkStats(requestParam);
    }

    /**
     * paging query short link monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/admin/v1/stats/access-record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkRemoteService.shortLinkStatsAccessRecord(requestParam);
    }

    /**
     * paging query group monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/admin/v1/stats/access-record/group")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return shortLinkRemoteService.groupShortLinkStatsAccessRecord(requestParam);
    }

}
