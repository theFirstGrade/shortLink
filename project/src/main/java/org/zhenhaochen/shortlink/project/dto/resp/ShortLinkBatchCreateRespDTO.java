package org.zhenhaochen.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
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