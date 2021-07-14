package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author tianhuan
 * @ClassName:ApplicationRun
 * @Description:
 * @date 2019/5/8 - 18:44
 */
@SpringBootApplication
@EnableEurekaServer
public class EdServerApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(EdServerApplicationRun.class,args);
    }
}
