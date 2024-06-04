package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zhenhaochen.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * Network Access Statistic Entity
 */
@Data
@TableName("t_link_network_stats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkNetworkStatsDO extends BaseDO {

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
     * network
     */
    private String network;
}

