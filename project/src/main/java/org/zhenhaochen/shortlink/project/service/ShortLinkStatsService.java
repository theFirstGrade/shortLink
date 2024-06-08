package org.zhenhaochen.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
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

    /**
     * get a group of short links monitor statistic
     */
    ShortLinkStatsRespDTO groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam);

    /**
     * paging query short link monitor statistic
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);

    /**
     * paging query group monitor statistic
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam);

}
