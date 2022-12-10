package com.bchen.tutorial.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("timerInterceptor")
    private HandlerInterceptor timerInterceptor;

    @Autowired
    @Qualifier("scheduleInterceptor")
    private HandlerInterceptor scheduleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(timerInterceptor).addPathPatterns("/auto-user-*");
        registry.addInterceptor(scheduleInterceptor).addPathPatterns("/support", "/support/");
    }

}
