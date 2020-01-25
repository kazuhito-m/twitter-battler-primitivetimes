package com.github.kazuhito_m.twitterbattler.primitive;

import com.github.kazuhito_m.twitterbattler.primitive.presentation.interceptor.LoggingHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoggingHandlerInterceptor loggingHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingHandlerInterceptor);
    }

    public WebConfig(LoggingHandlerInterceptor loggingHandlerInterceptor) {
        this.loggingHandlerInterceptor = loggingHandlerInterceptor;
    }
}
