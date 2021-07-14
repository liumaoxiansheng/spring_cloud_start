package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;

/**
 * @className: CloudGateWayApp
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/8
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class CloudGateWayApp {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateWayApp.class,args);
    }

    //@Bean
    public ServerCodecConfigurer serverCodecConfigurer(){
       return new DefaultServerCodecConfigurer();
    }
}
