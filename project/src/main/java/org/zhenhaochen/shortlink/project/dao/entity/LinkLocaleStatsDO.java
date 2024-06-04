package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zhenhaochen.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * 地区统计访问实体
 */
@Data
@TableName("t_link_locale_stats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkLocaleStatsDO extends BaseDO {

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
     * date
     */
    private Date date;

    /**
     * access count
     */
    private Integer cnt;

    /**
     * region name
     */
    private String province;

    /**
     * city
     */
    private String city;

    /**
     * postal code
     */
    private String postal;

    /**
     * country
     */
    private String country;
}
