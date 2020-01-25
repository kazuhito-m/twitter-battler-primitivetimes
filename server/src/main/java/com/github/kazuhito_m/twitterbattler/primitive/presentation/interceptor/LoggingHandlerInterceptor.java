package com.github.kazuhito_m.twitterbattler.primitive.presentation.interceptor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggingHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandlerInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("preHandle");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LOGGER.info("postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LOGGER.info("afterCompletion");
        if (!(handler instanceof HandlerMethod)) return;
        HandlerMethod method = (HandlerMethod) handler;
        String log = ToStringBuilder.reflectionToString(method, ToStringStyle.MULTI_LINE_STYLE);
        LOGGER.info("HandlerMethodの中身↓");
        LOGGER.info(log);
        LOGGER.info("response.getStatus():{}", response.getStatus());
        LOGGER.info("getMethod:{}", method.getMethod());
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("afterConcurrentHandlingStarted");
    }
}
