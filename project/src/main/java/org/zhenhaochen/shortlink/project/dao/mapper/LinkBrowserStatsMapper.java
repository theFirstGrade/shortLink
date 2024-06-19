package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkBrowserStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Browser Access Statistic Persistence Layer
 */
public interface LinkBrowserStatsMapper extends BaseMapper<LinkBrowserStatsDO> {

    /**
     * browser statistic
     */
    @Insert("""
            INSERT INTO t_link_browser_stats (
                full_short_url, 
                date, 
                cnt, 
                browser, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkBrowserStats.fullShortUrl}, 
                #{linkBrowserStats.date}, 
                #{linkBrowserStats.cnt}, 
                #{linkBrowserStats.browser}, 
                NOW(), 
                NOW(), 
                0
            ) ON DUPLICATE KEY UPDATE 
                update_time = NOW(), 
                cnt = cnt + #{linkBrowserStats.cnt};
            """)
    void shortLinkBrowserState(@Param("linkBrowserStats") LinkBrowserStatsDO linkBrowserStatsDO);

    /**
     * get browser monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlbs.browser, " +
            "    SUM(tlbs.cnt) AS count " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_browser_stats tlbs ON tl.full_short_url = tlbs.full_short_url " +
            "WHERE " +
            "    tlbs.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status =  #{param.enableStatus} " +
            "    AND tlbs.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlbs.full_short_url, tl.gid, tlbs.browser;")
    List<HashMap<String, Object>> listBrowserStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of browser monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlbs.browser, " +
            "    SUM(tlbs.cnt) AS count " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_browser_stats tlbs ON tl.full_short_url = tlbs.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlbs.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlbs.browser;")
    List<HashMap<String, Object>> listBrowserStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);
}
