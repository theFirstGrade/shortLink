package org.zhenhaochen.shortlink.admin.test;

public class UserTableShardingTest {

    public static final String SQL = "CREATE TABLE `t_link_routing_%d`\n" +
                                     "(\n" +
                                     "    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
                                     "    `gid`            varchar(32)  DEFAULT 'default' COMMENT 'Group Identifier',\n" +
                                     "    `full_short_url` varchar(128) DEFAULT NULL COMMENT 'Full Short Link',\n" +
                                     "    PRIMARY KEY (`id`)\n" +
                                     ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;\n";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
