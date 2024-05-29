package org.zhenhaochen.shortlink.project.dto.resp;

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
    private Integer validDateType;

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