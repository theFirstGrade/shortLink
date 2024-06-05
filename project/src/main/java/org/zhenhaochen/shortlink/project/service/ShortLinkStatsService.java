package org.zhenhaochen.shortlink.project.service;

import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * Short Link Monitor Interface Layer
 */
public interface ShortLinkStatsService {

    /**
     * get single short link monitor statistic
     * @return monitor statistic
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);
}
