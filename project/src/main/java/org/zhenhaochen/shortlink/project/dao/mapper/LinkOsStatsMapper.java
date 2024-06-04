package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.LinkOsStatsDO;

/**
 * Operating System Statistic Persistence Layer
 */
public interface LinkOsStatsMapper extends BaseMapper<LinkOsStatsDO> {

    /**
     * operating system statistic
     */
    @Insert("""
        INSERT INTO t_link_os_stats (
            full_short_url, 
            gid, 
            date, 
            cnt, 
            os, 
            create_time, 
            update_time, 
            del_flag
        ) VALUES (
            #{linkOsStats.fullShortUrl}, 
            #{linkOsStats.gid}, 
            #{linkOsStats.date}, 
            #{linkOsStats.cnt}, 
            #{linkOsStats.os}, 
            NOW(), 
            NOW(), 
            0
        ) ON DUPLICATE KEY UPDATE 
            update_time = NOW(), 
            cnt = cnt + #{linkOsStats.cnt};
        """)
    void shortLinkOsState(@Param("linkOsStats") LinkOsStatsDO linkOsStatsDO);
}

