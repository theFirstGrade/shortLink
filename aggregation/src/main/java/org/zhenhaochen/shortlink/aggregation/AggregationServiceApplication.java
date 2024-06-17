package org.zhenhaochen.shortlink.aggregation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Short Link Aggregation
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
		"org.zhenhaochen.shortlink.admin",
		"org.zhenhaochen.shortlink.project",
		"org.zhenhaochen.shortlink.aggregation"
})
@MapperScan(value = {
		"org.zhenhaochen.shortlink.project.dao.mapper",
		"org.zhenhaochen.shortlink.admin.dao.mapper"
})
public class AggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregationServiceApplication.class, args);
	}
}