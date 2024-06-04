package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.LinkBrowserStatsDO;

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
}
