package org.zhenhaochen.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.zhenhaochen.shortlink.project.dao.entity.ShortLinkDO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.zhenhaochen.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;

/**
 * Short Link Persistence Layer
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    /**
     * increment pv, uv, uip when restoring short link
     */
    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalPv") Integer totalPv,
            @Param("totalUv") Integer totalUv,
            @Param("totalUip") Integer totalUip
    );

    /**
     * paging statistic
     */
    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);

    /**
     * paging query recycle bin
     */
    IPage<ShortLinkDO> pageRecycleBinLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
