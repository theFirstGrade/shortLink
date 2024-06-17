package org.zhenhaochen.shortlink.admin.remote;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhenhaochen.shortlink.admin.common.convention.result.Result;
import org.zhenhaochen.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import org.zhenhaochen.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import org.zhenhaochen.shortlink.admin.remote.dto.req.*;
import org.zhenhaochen.shortlink.admin.remote.dto.resp.*;

import java.util.List;

/**
 * Short Link Admin Remote Revoke
 */
@FeignClient(value = "short-link-project", url = "${aggregation.remote-url:}")
public interface ShortLinkActualRemoteService {

    /**
     * create short link
     */
    @PostMapping("/api/short-link/v1/create")
    Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam);

    /**
     * batch create short link
     */
    @PostMapping("/api/short-link/v1/create/batch")
    Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam);

    /**
     * update short link
     */
    @PostMapping("/api/short-link/v1/update")
    void updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam);

    /**
     * short link paging query
     */
    @GetMapping("/api/short-link/v1/page")
    Result<Page<ShortLinkPageRespDTO>> pageShortLink(@SpringQueryMap ShortLinkPageReqDTO requestParam);

    /**
     * query short link count in a group
     *
     * @return short link count Object
     */
    @GetMapping("/api/short-link/v1/count")
    Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam);


    /**
     * get title by url
     *
     * @param url target url
     * @return title
     */
    @GetMapping("/api/short-link/v1/title")
    Result<String> getTitleByUrl(@RequestParam("url") String url);


    /**
     * move to recycle bin
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    void saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam);

    /**
     * recycle bin paging query
     */
    @GetMapping("/api/short-link/v1/recycle-bin/page")
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(@SpringQueryMap ShortLinkRecycleBinPageReqDTO requestParams);

    /**
     * recover short link
     */
    @PostMapping("/api/short-link/v1/recycle-bin/recover")
    void recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam);


    /**
     * remove short link
     */
    @PostMapping("/api/short-link/v1/recycle-bin/remove")
    void removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam);


    /**
     * get single short link monitor statistic between specified dates
     * @return short link monitor statistic
     */
    @GetMapping("/api/short-link/v1/stats")
    Result<ShortLinkStatsRespDTO> oneShortLinkStats(@SpringQueryMap ShortLinkStatsReqDTO requestParams);


    /**
     * get a group of short links monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/v1/stats/group")
    Result<ShortLinkStatsRespDTO> groupShortLinkStats(@SpringQueryMap ShortLinkGroupStatsReqDTO requestParams);


    /**
     * paging query short link monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/v1/stats/access-record")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(@SpringQueryMap ShortLinkStatsAccessRecordReqDTO requestParams);


    /**
     * paging query group monitor statistic between specified dates
     */
    @GetMapping("/api/short-link/v1/stats/access-record/group")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(@SpringQueryMap ShortLinkGroupStatsAccessRecordReqDTO requestParams);

}
