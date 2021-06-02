package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tianhuan
 * @ClassName:DoctorSeverApp
 * @Description:
 * @date 2019/5/13 - 11:15
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = {"com.demo.dao"})
public class DoctorSeverApp {
    public static void main(String[] args) {
        SpringApplication.run(DoctorSeverApp.class,args);
    }
}
