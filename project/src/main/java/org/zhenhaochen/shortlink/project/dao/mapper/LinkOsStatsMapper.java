package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkOsStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

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
                date, 
                cnt, 
                os, 
                create_time, 
                update_time, 
                del_flag
            ) VALUES (
                #{linkOsStats.fullShortUrl}, 
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

    /**
     * get OS monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlos.os, " +
            "    SUM(tlos.cnt) AS count " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_os_stats tlos ON tl.full_short_url = tlos.full_short_url " +
            "WHERE " +
            "    tlos.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status =  #{param.enableStatus} " +
            "    AND tlos.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlos.full_short_url, tl.gid, tlos.os;")
    List<HashMap<String, Object>> listOsStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of OS monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    tlos.os, " +
            "    SUM(tlos.cnt) AS count " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_os_stats tlos ON tl.full_short_url = tlos.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlos.date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlos.os;")
    List<HashMap<String, Object>> listOsStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);

}

