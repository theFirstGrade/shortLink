package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkDeviceStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

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
                date, 
                cnt, 
                device, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkDeviceStats.fullShortUrl}, 
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

    /**
     * get device monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlds.device, " +
            "    SUM(tlds.cnt) AS cnt " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_device_stats tlds ON tl.full_short_url = tlds.full_short_url " +
            "WHERE " +
            "    tlds.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status =  #{param.enableStatus} " +
            "    AND tlds.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlds.full_short_url, tl.gid, tlds.device;")
    List<LinkDeviceStatsDO> listDeviceStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of device monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlds.device, " +
            "    SUM(tlds.cnt) AS cnt " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_device_stats tlds ON tl.full_short_url = tlds.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlds.date BETWEEN #{param.startDate} and #{param.endDate} " +

            "GROUP BY " +
            "    tl.gid, tlds.device;")
    List<LinkDeviceStatsDO> listDeviceStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);

}
