package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: FlowDesignApplication
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/6
 **/
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = {"com.demo.dao"})
public class FlowDesignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowDesignApplication.class,args);
    }
}
