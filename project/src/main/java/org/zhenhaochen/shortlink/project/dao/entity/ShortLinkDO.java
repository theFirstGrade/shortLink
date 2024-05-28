package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.zhenhaochen.shortlink.project.common.database.BaseDO;

import java.util.Date;

/**
 * Short Link Entity
 */
@Data
@TableName("t_link")
public class ShortLinkDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * domain name
     */
    private String domain;

    /**
     * short uri
     */
    private String shortUri;

    /**
     * full short link
     */
    private String fullShortUrl;

    /**
     * origin url
     */
    private String originUrl;

    /**
     * click Count
     */
    private Integer clickNum;

    /**
     * gid
     */
    private String gid;

    /**
     * flag 0: started 1: not started
     */
    private Integer enableStatus;

    /**
     * created type 0: console 1: interface
     */
    private Integer createdType;

    /**
     * validate type 0: forever 1: customized
     */
    private Integer validDateType;

    /**
     * validate period
     */
    private Date validDate;

    /**
     * description
     */
//    @TableField("`describe`")
    private String description;
}