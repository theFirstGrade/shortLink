package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.LinkLocaleStatsDO;

/**
 * Region Statistics Persistence Layer
 */
public interface LinkLocaleStatsMapper extends BaseMapper<LinkLocaleStatsDO> {

    /**
     * region statistics
     */
    @Insert("""
        INSERT INTO t_link_locale_stats (
            full_short_url, 
            gid, 
            date, 
            cnt, 
            country, 
            province, 
            city, 
            postal, 
            create_time, 
            update_time, 
            del_flag
        ) VALUES (
            #{linkLocaleStats.fullShortUrl}, 
            #{linkLocaleStats.gid}, 
            #{linkLocaleStats.date}, 
            #{linkLocaleStats.cnt}, 
            #{linkLocaleStats.country}, 
            #{linkLocaleStats.province}, 
            #{linkLocaleStats.city}, 
            #{linkLocaleStats.postal}, 
            NOW(), 
            NOW(), 
            0
        ) ON DUPLICATE KEY UPDATE 
            update_time = NOW(), 
            cnt = cnt + #{linkLocaleStats.cnt};
        """)
    void shortLinkLocaleState(@Param("linkLocaleStats") LinkLocaleStatsDO linkLocaleStatsDO);
}

