package com.spir.ggcycling.config;

import com.spir.ggcycling.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by spir
 * Date2019/4/17 Time 1:21
 */
@Configuration
public class Myconfig implements WebMvcConfigurer {

    @Autowired
    MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).excludePathPatterns("/")
                .excludePathPatterns("/home")
                .excludePathPatterns("/news/{newsId}")
                .excludePathPatterns("/user/{userId}/")
                //登录注册
                .excludePathPatterns("/login/")
                .excludePathPatterns("/register")
                //样式
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                //图片
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.jpeg")
                .excludePathPatterns("/**/*.gif");

    }
}
