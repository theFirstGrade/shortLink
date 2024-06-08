package org.zhenhaochen.shortlink.admin.remote.dto.resp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Basic Information Response Object
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkBaseInfoRespDTO {

    /**
     * description
     */
    @ExcelProperty("description")
    @ColumnWidth(40)
    private String description;

    /**
     * original url
     */
    @ExcelProperty("origin short link")
    @ColumnWidth(80)
    private String originUrl;

    /**
     * full short link
     */
    @ExcelProperty("full short link")
    @ColumnWidth(40)
    private String fullShortUrl;
}