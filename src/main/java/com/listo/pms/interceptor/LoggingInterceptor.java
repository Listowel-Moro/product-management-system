package com.listo.pms.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, startTime);

        System.out.println("Incoming request: ");
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Headers: " + request.getHeaderNames());
        System.out.println("Query Params: " + request.getQueryString());

        logger.info("Incoming request: URI={}, Method={}, QueryParams={}",
                request.getRequestURI(), request.getMethod(), request.getQueryString());

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle: Handler executed, status code: " + response.getStatus());
        logger.info("PostHandle: Handler executed, status code={}", response.getStatus());
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute(START_TIME);
        long endTime = System.currentTimeMillis();

        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        logger.info("Request URI={}, Execution Time={} ms"  + "\n", request.getRequestURI(), (endTime - startTime));

        if (ex != null) {
            System.err.println("Exception occurred: " + ex.getMessage());
        }
    }
}
