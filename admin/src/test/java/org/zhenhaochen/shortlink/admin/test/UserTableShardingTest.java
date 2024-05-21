package org.zhenhaochen.shortlink.admin.test;

public class UserTableShardingTest {

    public static final String SQL = "CREATE TABLE `t_user_%d` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `username` varchar(256) DEFAULT NULL COMMENT 'username',\n" +
            "  `password` varchar(512) DEFAULT NULL COMMENT 'password',\n" +
            "  `real_name` varchar(256) DEFAULT NULL COMMENT 'real name',\n" +
            "  `phone` varchar(128) DEFAULT NULL COMMENT 'phone number',\n" +
            "  `mail` varchar(512) DEFAULT NULL COMMENT 'mail',\n" +
            "  `deletion_time` bigint(20) DEFAULT NULL COMMENT 'deleted time',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT 'created time',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT 'updated time',\n" +
            "  `del_flag` tinyint(1) DEFAULT NULL COMMENT 'delete flag 0: undeleted 1: deleted',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `idx_unique_username` (`username`) USING BTREE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1715030926162935810 DEFAULT CHARSET=utf8mb4;";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
