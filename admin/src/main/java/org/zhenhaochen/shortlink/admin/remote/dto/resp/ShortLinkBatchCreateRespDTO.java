package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Batch Create Short Link Response Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkBatchCreateRespDTO {

    /**
     * total successful creation number
     */
    private Integer total;

    /**
     * basic short link information
     */
    private List<ShortLinkBaseInfoRespDTO> baseLinkInfos;
}