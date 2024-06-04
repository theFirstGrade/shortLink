package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.LinkAccessStatsDO;

/**
 * Short Link Basic Access Supervised Persistence Layer
 */
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {

    /**
     * pv, uv, uip
     */
    @Insert("""
        INSERT INTO t_link_access_stats (
            full_short_url, 
            gid, 
            date, 
            pv, 
            uv, 
            uip, 
            hour, 
            weekday, 
            create_time, 
            update_time, 
            del_flag
        ) VALUES (
            #{linkAccessStats.fullShortUrl}, 
            #{linkAccessStats.gid}, 
            #{linkAccessStats.date}, 
            #{linkAccessStats.pv}, 
            #{linkAccessStats.uv}, 
            #{linkAccessStats.uip}, 
            #{linkAccessStats.hour}, 
            #{linkAccessStats.weekday}, 
            NOW(), 
            NOW(), 
            0
        ) ON DUPLICATE KEY UPDATE 
            update_time = NOW(), 
            pv = pv + #{linkAccessStats.pv}, 
            uv = uv + #{linkAccessStats.uv}, 
            uip = uip + #{linkAccessStats.uip};
        """)
    void shortLinkStats(@Param("linkAccessStats") LinkAccessStatsDO linkAccessStatsDO);
}
