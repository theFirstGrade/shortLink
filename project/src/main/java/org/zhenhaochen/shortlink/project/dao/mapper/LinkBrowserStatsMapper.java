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
                gid, 
                date, 
                cnt, 
                browser, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkBrowserStats.fullShortUrl}, 
                #{linkBrowserStats.gid}, 
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
            "    browser, " +
            "    SUM(cnt) AS count " +
            "FROM " +
            "    t_link_browser_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, browser;")
    List<HashMap<String, Object>> listBrowserStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of browser monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    browser, " +
            "    SUM(cnt) AS count " +
            "FROM " +
            "    t_link_browser_stats " +
            "WHERE " +
            "    gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    gid, browser;")
    List<HashMap<String, Object>> listBrowserStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);
}
