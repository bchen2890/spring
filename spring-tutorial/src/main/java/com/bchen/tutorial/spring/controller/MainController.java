package com.bchen.tutorial.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @RequestMapping(value={"/index", "/", "/home"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @GetMapping("profile")
    public String profile(@RequestParam String name, @RequestParam Integer age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "profile";
    }

}
