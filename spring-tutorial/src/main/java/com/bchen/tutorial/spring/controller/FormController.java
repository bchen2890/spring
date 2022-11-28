package com.bchen.tutorial.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @GetMapping("/form")
    public String getForm (){
        return "form";
    }

    @PostMapping("/form")
    public String postForm (@RequestParam String username, @RequestParam String password,
                            @RequestParam String email, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("email", email);
        return "logged";
    }
}
