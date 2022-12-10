package com.bchen.tutorial.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/support")
public class SupportController {

    @Value("${schedule.start}")
    private Integer start;

    @Value("${schedule.end}")
    private Integer end;

    @GetMapping({"", "/"})
    public String getSupport (){
        return "support/index";
    }

    @GetMapping("/unavailable")
    public String getUnvailable(Model model){
        String message = String.format("Sorry, we are unavailable now. We are available from %d:00 to %d:00.", start, end);
        model.addAttribute("message", message);
        return "support/unavailable";
    }
}
