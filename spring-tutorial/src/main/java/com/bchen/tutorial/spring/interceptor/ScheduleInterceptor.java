package com.bchen.tutorial.spring.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component("scheduleInterceptor")
public class ScheduleInterceptor implements HandlerInterceptor {
    @Value("${schedule.start}")
    private Integer start;

    @Value("${schedule.end}")
    private Integer end;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour > start && hour < end){
            String message = String.format("We are available from %d:00 to %d:00.", start, end);
            request.setAttribute("message", message);
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/support/unavailable"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        String message = (String) request.getAttribute("message");
        if(modelAndView != null && handler instanceof HandlerMethod){
            modelAndView.addObject("message", message);
        }
    }
}
