package com.example.zuulservice.fliter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    @Override
    public Object run() throws ZuulException {  // 사용자의 요청이 들어올 때 마다 실행됨.
        log.info("************** printing logs: ");

        RequestContext ctx = RequestContext.getCurrentContext();  // Request 정보를 가진 최상위 객체
        HttpServletRequest request = ctx.getRequest();  // Request 정보

        log.info("************** " + request.getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

}
