package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.error.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(NumberFormatException.class)
    public String numberFormat(NumberFormatException ex, Model model){
        model.addAttribute("error", "Number Format Error");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/generic";
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public String roleNotFound(RoleNotFoundException ex, Model model){
        model.addAttribute("error", "Role Not Found");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());
        return "error/generic";
    }
}
