package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.LinkNetworkStatsDO;

/**
 * Device Access Statistic Persistence Layer
 */
public interface LinkNetworkStatsMapper extends BaseMapper<LinkNetworkStatsDO> {

    /**
     * network statistic
     */
    @Insert("""
            INSERT INTO t_link_network_stats (
                full_short_url, 
                gid, 
                date, 
                cnt, 
                network, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkNetworkStats.fullShortUrl}, 
                #{linkNetworkStats.gid}, 
                #{linkNetworkStats.date}, 
                #{linkNetworkStats.cnt}, 
                #{linkNetworkStats.network}, 
                NOW(), 
                NOW(), 
                0
            ) ON DUPLICATE KEY UPDATE 
                update_time = NOW(), 
                cnt = cnt + #{linkNetworkStats.cnt};
            """)
    void shortLinkNetworkState(@Param("linkNetworkStats") LinkNetworkStatsDO linkNetworkStatsDO);
}

