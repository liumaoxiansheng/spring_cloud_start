package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @className: MyCrosConfig
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/7
 **/
@Configuration
public class MyCrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    /**
     * 通过@Bean注解，将我们定义的拦截器注册到Spring容器
     * @return
     */
    @Bean
    public MyHandlerInterceptor myInterceptor(){
        return new MyHandlerInterceptor();
    }

    /**
     * 重写接口中的addInterceptors方法，添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过registry来注册拦截器，通过addPathPatterns来添加拦截路径
        registry.addInterceptor(this.myInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                System.out.println(" origin : "+request.getHeaders().get(HttpHeaders.ORIGIN).get(0));
                headers.setAccessControlAllowOrigin((request.getHeaders().get(HttpHeaders.ORIGIN)).get(0));
                headers.setAccessControlAllowCredentials(true);
                headers.setAccessControlMaxAge(Integer.MAX_VALUE);
                headers.setAccessControlAllowHeaders(Arrays.asList("*"));
                headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.OPTIONS,
                        HttpMethod.GET, HttpMethod.HEAD, HttpMethod.POST,
                        HttpMethod.DELETE, HttpMethod.PUT));
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}
