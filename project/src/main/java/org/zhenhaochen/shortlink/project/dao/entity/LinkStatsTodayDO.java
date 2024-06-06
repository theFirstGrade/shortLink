package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.zhenhaochen.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * Short Link Daily Statistic Entity
 */
@TableName("t_link_stats_today")
@Data
public class LinkStatsTodayDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * gid
     */
    private String gid;

    /**
     * full short link
     */
    private String fullShortUrl;

    /**
     * date
     */
    private Date date;

    /**
     * today pv
     */
    private Integer todayPv;

    /**
     * today uv
     */
    private Integer todayUv;

    /**
     * today uip
     */
    private Integer todayIpCount;
}
