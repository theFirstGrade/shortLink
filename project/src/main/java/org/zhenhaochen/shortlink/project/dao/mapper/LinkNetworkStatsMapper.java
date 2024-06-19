package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkNetworkStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

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
                date, 
                cnt, 
                network, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkNetworkStats.fullShortUrl}, 
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

    /**
     * get network monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlns.network, " +
            "    SUM(tlns.cnt) AS cnt " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_network_stats tlns ON tl.full_short_url = tlns.full_short_url " +
            "WHERE " +
            "    tlns.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status =  #{param.enableStatus} " +
            "    AND tlns.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlns.full_short_url, tl.gid, tlns.network;")
    List<LinkNetworkStatsDO> listNetworkStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of network monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlns.network, " +
            "    SUM(tlns.cnt) AS cnt " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_network_stats tlns ON tl.full_short_url = tlns.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlns.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlns.network;")
    List<LinkNetworkStatsDO> listNetworkStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);
}

