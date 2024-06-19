package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhenhaochen.shortlink.project.dao.entity.LinkAccessLogsDO;
import org.zhenhaochen.shortlink.project.dao.entity.LinkAccessStatsDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Log Access Persistence Layer
 */
public interface LinkAccessLogsMapper extends BaseMapper<LinkAccessLogsDO> {

    /**
     * get high frequency access IP statistic between specified dates
     */
    @Select("SELECT " +
            "    tlal.ip, " +
            "    COUNT(tlal.ip) AS count " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tlal.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status =  #{param.enableStatus} " +
            "    AND tlal.create_time BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlal.full_short_url, tl.gid, tlal.ip " +
            "ORDER BY " +
            "    count DESC " +
            "LIMIT 5;")
    List<HashMap<String, Object>> listTopIpByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of high frequency access IP statistic between specified dates
     */
    @Select("SELECT " +
            "    tlal.ip, " +
            "    COUNT(tlal.ip) AS count " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlal.create_time BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid, tlal.ip " +
            "ORDER BY " +
            "    count DESC " +
            "LIMIT 5;")
    List<HashMap<String, Object>> listTopIpByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);

    /**
     * get old/new visitor statistic between specified dates
     * visitors with more than one access and are between specified dates are old users
     * visitors with one access and are between specified dates are new users
     */
    @Select("SELECT " +
            "    SUM(old_user) AS oldUserCnt, " +
            "    SUM(new_user) AS newUserCnt " +
            "FROM ( " +
            "    SELECT " +
            "        CASE WHEN COUNT(DISTINCT DATE(tlal.create_time)) > 1 AND MAX(tlal.create_time) >= #{param.startDate} AND MAX(tlal.create_time) <= #{param.endDate} THEN 1 ELSE 0 END AS old_user, " +
            "        CASE WHEN COUNT(DISTINCT DATE(tlal.create_time)) = 1 AND MAX(tlal.create_time) >= #{param.startDate} AND MAX(tlal.create_time) <= #{param.endDate} THEN 1 ELSE 0 END AS new_user " +
            "    FROM " +
            "        t_link tl INNER JOIN " +
            "        t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "    WHERE " +
            "        tlal.full_short_url = #{param.fullShortUrl} " +
            "        AND tl.gid = #{param.gid} " +
            "        AND tl.enable_status =  #{param.enableStatus} " +
            "        AND tl.del_flag = '0' " +
            "    GROUP BY " +
            "        tlal.user " +
            ") AS user_counts;")
    HashMap<String, Object> findUvTypeCntByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * judge visitor type
     * if the minimum create time is between specified dates then new visitor
     */
    @Select("<script> " +
            "SELECT " +
            "    tlal.user, " +
            "    CASE " +
            "        WHEN MIN(tlal.create_time) BETWEEN #{startDate} AND #{endDate} THEN 'new visitor' " +
            "        ELSE 'old visitor' " +
            "    END AS uvType " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tlal.full_short_url = #{fullShortUrl} " +
            "    AND tl.gid = #{gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = #{enableStatus} " +
            "    AND tlal.user IN " +
            "    <foreach item='item' index='index' collection='userAccessLogsList' open='(' separator=',' close=')'> " +
            "        #{item} " +
            "    </foreach> " +
            "GROUP BY " +
            "    tlal.user;" +
            "</script>"
    )
    List<Map<String, Object>> selectUvTypeByUsers(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("enableStatus") Integer enableStatus,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("userAccessLogsList") List<String> userAccessLogsList
    );

    /**
     * judge visitor type
     * if the minimum create time is between specified dates then new visitor
     */
    @Select("<script> " +
            "SELECT " +
            "    tlal.user, " +
            "    CASE " +
            "        WHEN MIN(tlal.create_time) BETWEEN #{startDate} AND #{endDate} THEN 'new visitor' " +
            "        ELSE 'old visitor' " +
            "    END AS uvType " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tl.gid = #{gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlal.user IN " +
            "    <foreach item='item' index='index' collection='userAccessLogsList' open='(' separator=',' close=')'> " +
            "        #{item} " +
            "    </foreach> " +
            "GROUP BY " +
            "    tlal.user;" +
            "</script>"
    )
    List<Map<String, Object>> selectGroupUvTypeByUsers(
            @Param("gid") String gid,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("userAccessLogsList") List<String> userAccessLogsList
    );

    /**
     * get pv, uv, uip between specified dates
     */
    @Select("SELECT " +
            "    COUNT(tlal.user) AS pv, " +
            "    COUNT(DISTINCT tlal.user) AS uv, " +
            "    COUNT(DISTINCT tlal.ip) AS uip " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tlal.full_short_url = #{param.fullShortUrl} " +
            "    AND tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status =  #{param.enableStatus} " +
            "    AND tlal.create_time BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tlal.full_short_url, tl.gid;")
    LinkAccessStatsDO findPvUvUidStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

    /**
     * get a group of pv, uv, uip between specified dates
     */
    @Select("SELECT " +
            "    COUNT(tlal.user) AS pv, " +
            "    COUNT(DISTINCT tlal.user) AS uv, " +
            "    COUNT(DISTINCT tlal.ip) AS uip " +
            "FROM " +
            "    t_link tl INNER JOIN " +
            "    t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlal.create_time BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    tl.gid;")
    LinkAccessStatsDO findPvUvUidStatsByGroup(@Param("param") ShortLinkGroupStatsReqDTO requestParam);

    @Select("SELECT " +
            "    tlal.* " +
            "FROM " +
            "    t_link tl " +
            "    INNER JOIN t_link_access_logs tlal ON tl.full_short_url = tlal.full_short_url " +
            "WHERE " +
            "    tl.gid = #{param.gid} " +
            "    AND tl.del_flag = '0' " +
            "    AND tl.enable_status = '0' " +
            "    AND tlal.create_time BETWEEN #{param.startDate} and #{param.endDate} " +
            "ORDER BY " +
            "    tlal.create_time DESC")
    IPage<LinkAccessLogsDO> selectGroupPage(@Param("param") ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}