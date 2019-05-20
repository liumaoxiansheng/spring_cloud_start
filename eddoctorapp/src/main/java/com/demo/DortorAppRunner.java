package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author tianhuan
 * @ClassName:AppRunner
 * @Description:
 * @date 2019/5/8 - 18:51
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class DortorAppRunner {
    public static void main(String[] args) {
        SpringApplication.run(DortorAppRunner.class,args);
    }

    @Bean
    @LoadBalanced  // 使用负载均衡器Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
