package com.binhui.example.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error,
                        @RequestParam(value="logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash) {

        if(principal != null) {
            flash.addFlashAttribute("info", "You're already logged");
            return "redirect:/";
        }

        if(error != null) {
            model.addAttribute("error", "Username or password incorrect");
        }

        if(logout != null) {
            model.addAttribute("success", "You have been signed out!");
        }

        return "login";
    }
}
