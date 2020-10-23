package net.xdclass.online_xdclass.config;

import net.xdclass.online_xdclass.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @ClassName : InterceptorConfig  //类名
 * @Description : 拦截品配置  不用权限可以访问url /api/v1/pub/  要权限的/api/v1/pri/
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-23 21:05  //时间
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    LoginInterceptor loginInterceptor(){
        return  new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
