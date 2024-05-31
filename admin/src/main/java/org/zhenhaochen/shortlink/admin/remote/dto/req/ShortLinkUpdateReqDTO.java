package org.zhenhaochen.shortlink.admin.remote.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short Link Update Request Object
 */
@Data
public class ShortLinkUpdateReqDTO {

    /**
     * original url
     */
    private String originUrl;

    /**
     * full short url
     */
    private String fullShortUrl;

    /**
     * gid
     */
    private String gid;

    /**
     * validate type 0: forever 1: customized
     */
    private Integer validDateType;

    /**
     * validity period
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * description
     */
    private String description;
}
