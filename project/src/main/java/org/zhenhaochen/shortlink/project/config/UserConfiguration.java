package org.zhenhaochen.shortlink.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zhenhaochen.shortlink.project.common.biz.user.UserTransmitInterceptor;

/**
 * User Configuration
 */
@Configuration(value = "userConfigurationByProject")
@RequiredArgsConstructor
public class UserConfiguration implements WebMvcConfigurer {

    private final UserTransmitInterceptor userTransmitInterceptor;

    /**
     * user information interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTransmitInterceptor)
                .addPathPatterns("/**");
    }
}
