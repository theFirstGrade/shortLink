package org.zhenhaochen.shortlink.project.dao.mapper;

import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

/**
 * Short Link Persistence Layer
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    /**
     * increment pv, uv, uip when restoring short link
     */
    @Update("update t_link set total_pv = total_pv + #{totalPv}, total_uv = total_uv + #{totalUv}, total_uip = total_uip + #{totalUip} where gid = #{gid} and full_short_url = #{fullShortUrl}")
    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalPv") Integer totalPv,
            @Param("totalUv") Integer totalUv,
            @Param("totalUip") Integer totalUip
    );
}
