package org.zhenhaochen.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class UserDO {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * real_name
     */
    private String realName;

    /**
     * phone
     */
    private String phone;

    /**
     * mail
     */
    private String mail;

    /**
     * deletion_time
     */
    private Long deletionTime;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * del_flag
     */
    private Integer delFlag;

}
