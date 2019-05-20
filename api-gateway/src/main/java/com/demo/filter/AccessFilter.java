package com.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianhuan
 * @ClassName:AccessFilter
 * @Description:
 * @date 2019/5/17 - 11:59
 */
@Slf4j
@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
         RequestContext context = RequestContext.getCurrentContext();
         HttpServletRequest request = context.getRequest();
         log.info("请求过滤====》{},URL------>{}",request.getMethod(),request.getRequestURI().toString());
         String token = request.getParameter("token");
        if (StringUtils.isBlank(token)){
            log.info("未授权....");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(403);
            return null;
        }
        log.info("授权访问.....");
        return null;
    }
}
