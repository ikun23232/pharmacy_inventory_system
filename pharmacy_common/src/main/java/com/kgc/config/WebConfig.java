package com.kgc.config;

import com.kgc.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login")
                .addPathPatterns("/**");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //项目中的所有接口都支持跨域
        registry.addMapping("/**")
                //所有地址都可以访问，也可以配置具体地址
                .allowedOrigins("*")
                //允许的请求方式
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //是否支持跨域Cookie
                .allowCredentials(true)
                // 跨域允许时间
                .maxAge(3600);
    }

}