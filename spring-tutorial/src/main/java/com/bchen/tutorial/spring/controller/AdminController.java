package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping({"/", ""})
    public String profile(@RequestParam(name="username", defaultValue = "admin") String username, Model model){
        model.addAttribute("username", username);
        return "admin/index";
    }

    @GetMapping("/view/{name}/{age}")
    public String view(@PathVariable String name, @PathVariable Integer age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "admin/view";
    }

    @RequestMapping("/list")
    public String listar(Model model) {
        return "admin/list";
    }

    @ModelAttribute("users")
    public List<User> userList(){
        List<User> Users = Arrays.asList(new User("Ana", 19),
                new User("Juan", 21),
                new User("Alex", 23),
                new User("Betty", 22));
        return Users;
    }
}
