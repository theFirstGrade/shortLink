package org.zhenhaochen.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Batch Create Short Link Object
 */
@Data
public class ShortLinkBatchCreateReqDTO {

    /**
     * original url list
     */
    private List<String> originUrls;

    /**
     * description list
     */
    private List<String> descriptions;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private Date validDate;
}
