package com.xiaofan.car;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
@SpringBootApplication
@MapperScan("com.xiaofan.car.dao.repository")
public class AppStartConfiguration{
    private static final Logger log = LoggerFactory.getLogger(AppStartConfiguration.class);

    public static void main(String[] args) {
        SpringApplication app =new SpringApplication(AppStartConfiguration.class);
//		DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        try {
            log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}\n\t" +
                            "External: \thttp://{}:{}\n----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }

        String configServerStatus = env.getProperty("configserver.status");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Config Server: \t{}\n----------------------------------------------------------",
                configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }
}
