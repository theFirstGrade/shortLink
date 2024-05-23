package org.zhenhaochen.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_group")
public class GroupDO {

    /**
     * id
     */
    private Long id;

    /**
     * group identification
     */
    private String gid;

    /**
     * group name
     */
    private String name;

    /**
     * username
     */
    private String username;

    /**
     * created time
     */
    private Date createTime;

    /**
     * updated time
     */
    private Date updateTime;

    /**
     * delete flag 0: undeleted 1: deleted
     */
    private Integer delFlag;
}
