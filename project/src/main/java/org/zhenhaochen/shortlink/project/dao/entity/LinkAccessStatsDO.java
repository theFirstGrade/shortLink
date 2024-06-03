package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zhenhaochen.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * Short Link Basic Access Supervised Entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_link_access_stats")
public class LinkAccessStatsDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * full short link
     */
    private String fullShortUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * create date
     */
    private Date date;

    /**
     * page view
     */
    private Integer pv;

    /**
     * unique view
     */
    private Integer uv;

    /**
     * unique ip
     */
    private Integer uip;

    /**
     * hour view
     */
    private Integer hour;

    /**
     * weekday view
     */
    private Integer weekday;
}
