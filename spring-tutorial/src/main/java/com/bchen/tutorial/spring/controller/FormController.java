package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/user-form")
    public String getUserForm ( Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/user-form")
    public String userForm (@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                String field = err.getField();
                errors.put(field, "The field " + field + " " + err.getDefaultMessage());
            });
            model.addAttribute("errors", errors);
            return "userForm";
        }
        model.addAttribute("user", user);

        return "userLogged";
    }

    @GetMapping("/auto-user-form")
    public String getAutoUserForm ( Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "autoUserForm";
    }

    @PostMapping("/auto-user-form")
    public String autoUserForm (@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                String field = err.getField();
                errors.put(field, "The field " + field + " " + err.getDefaultMessage());
            });
            model.addAttribute("errors", errors);
            return "autoUserForm";
        }
        model.addAttribute("user", user);

        return "userLogged";
    }
}
