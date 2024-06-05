package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkLocaleStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.List;

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

    /**
     * get province monitor statistic between specified dates
     */
    @Select("SELECT " +
            "    province, " +
            "    SUM(cnt) AS cnt " +
            "FROM " +
            "    t_link_locale_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, province;")
    List<LinkLocaleStatsDO> listLocaleByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);
}

