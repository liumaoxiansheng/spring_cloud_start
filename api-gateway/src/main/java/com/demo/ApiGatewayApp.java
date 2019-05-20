package com.demo;

import com.demo.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author tianhuan
 * @ClassName:ApiGatewayApp
 * @Description:
 * @date 2019/5/17 - 10:58
 */
// exclude = DataSourceAutoConfiguration.class 项目不需要数据源但又导入了依赖，所以排除自动配置，否则会报错
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy // 开启网关服务
public class ApiGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApp.class,args);
    }

    /*@Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }*/
}
