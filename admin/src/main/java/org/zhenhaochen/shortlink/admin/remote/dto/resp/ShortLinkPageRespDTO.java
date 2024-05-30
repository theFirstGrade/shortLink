package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short Link Page Partition Response Params
 */
@Data
public class ShortLinkPageRespDTO {

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
     * full short url
     */
    private String fullShortUrl;

    /**
     * original url
     */
    private String originUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * validate type 0: forever 1: customized
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private Integer validDateType;

    /**
     * creation time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private Date createTime;

    /**
     * validate period
     */
    private Date validDate;

    /**
     * description
     */
    private String description;

    /**
     * icon
     */
    private String favicon;
}