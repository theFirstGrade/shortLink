package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkAccessStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

/**
 * Short Link Basic Access Monitor Persistence Layer
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

    /**
     * get basic monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    date, " +
            "    SUM(pv) AS pv, " +
            "    SUM(uv) AS uv, " +
            "    SUM(uip) AS uip " +
            "FROM " +
            "    t_link_access_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, date;")
    List<LinkAccessStatsDO> listStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get basic monitor statistic between specified hours
     */
    @Select("SELECT " +
            "    hour, " +
            "    SUM(pv) AS pv " +
            "FROM " +
            "    t_link_access_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, hour;")
    List<LinkAccessStatsDO> listHourStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get basic monitor statistic between specified weekday
     */
    @Select("SELECT " +
            "    weekday, " +
            "    SUM(pv) AS pv " +
            "FROM " +
            "    t_link_access_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, weekday;")
    List<LinkAccessStatsDO> listWeekdayStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);
}
