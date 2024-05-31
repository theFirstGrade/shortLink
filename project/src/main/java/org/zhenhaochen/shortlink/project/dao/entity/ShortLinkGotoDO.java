package org.zhenhaochen.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Short Link Routing Entity
 */
@Data
@Builder
@TableName("t_link_routing")
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkGotoDO {

    /**
     * ID
     */
    private Long id;

    /**
     * group identification
     */
    private String gid;

    /**
     * full short link url
     */
    private String fullShortUrl;
}
