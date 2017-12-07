package com.xiaofan.car.configuration;

import com.xiaofan.car.handler.JwtHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    //配置jwt拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtHandler())
                .addPathPatterns("/**")
                .excludePathPatterns("/error",
                        "/login",
                        "/swagger-resources",
                        "/v2/api-docs",
                        "/configuration/*",
                        "/v1/image/sosOutImg/*",
                        "/v1/image/upPic",
                        "/v1/file/download"
                );
        super.addInterceptors(registry);
    }

    //配置前端项目访问地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/car/");
        registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }
}
