package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.LinkDeviceStatsDO;

/**
 * Device Access Statistic Persistence Layer
 */
public interface LinkDeviceStatsMapper extends BaseMapper<LinkDeviceStatsDO> {

    /**
     * device statistic
     */
    @Insert("""
            INSERT INTO t_link_device_stats (
                full_short_url, 
                gid, 
                date, 
                cnt, 
                device, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkDeviceStats.fullShortUrl}, 
                #{linkDeviceStats.gid}, 
                #{linkDeviceStats.date}, 
                #{linkDeviceStats.cnt}, 
                #{linkDeviceStats.device}, 
                NOW(), 
                NOW(), 
                0
            ) ON DUPLICATE KEY UPDATE 
                update_time = NOW(), 
                cnt = cnt + #{linkDeviceStats.cnt};
            """)
    void shortLinkDeviceState(@Param("linkDeviceStats") LinkDeviceStatsDO linkDeviceStatsDO);
}
