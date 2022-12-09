package com.bchen.tutorial.spring.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component("timerInterceptor")
public class TimerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TimerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(request.getMethod().equalsIgnoreCase("post")) {
            return true;
        }

        if(handler instanceof HandlerMethod) {
            HandlerMethod metodo = (HandlerMethod) handler;
            logger.info("Controller method: " + metodo.getMethod().getName());
        }

        logger.info("TimerInterceptor: preHandle()");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        Random random = new Random();
        Integer delay = random.nextInt(100);
        Thread.sleep(delay);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        if(request.getMethod().equalsIgnoreCase("post"))
            return;


        long endTime = System.currentTimeMillis();
        long startTime = (Long) request.getAttribute("startTime");
        long timeElapsed = endTime - startTime;

        if(handler instanceof HandlerMethod && modelAndView != null) {
            modelAndView.addObject("timeElapsed", timeElapsed);
        }
        logger.info("Time taken: " + timeElapsed + " milliseconds");
        logger.info("TimerInterceptor: postHandle()");

    }
}
