package org.zhenhaochen.shortlink.project.dto.req;

import lombok.Data;

import java.util.Date;

/**
 * Short Link Creation Request Object
 */
@Data
public class ShortLinkCreateReqDTO {

    /**
     * domain name
     */
    private String domain;

    /**
     * origin url
     */
    private String originUrl;

    /**
     * gid
     */
    private String gid;

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
    private String description;
}