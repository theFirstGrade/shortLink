package org.zhenhaochen.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * User Information Filter
 */
@RequiredArgsConstructor
@Slf4j
public class UserTransmitFilter implements Filter {

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String username = httpServletRequest.getHeader("username");
        System.out.println(httpServletRequest.getHeader("username"));
        System.out.println(username + " ------ ");
        if (username == null || Objects.equals(username, "")) {
            log.error("Username is null");
        }

        if (StrUtil.isNotBlank(username)) {
            String userId = httpServletRequest.getHeader("userId");
            String realName = httpServletRequest.getHeader("realName");
            UserInfoDTO userInfoDTO = new UserInfoDTO(userId, username, realName);
            UserContext.setUser(userInfoDTO);
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } finally {
                UserContext.removeUser();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

