package com.xiaofan.car.configuration;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author: bean
 * @Description:
 * @Date: Create in 2017/8/26-16:55
 */
@Configuration
public class MybaitsConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
