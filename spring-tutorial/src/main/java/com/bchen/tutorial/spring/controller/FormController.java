package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.model.UppercaseEditor;
import com.bchen.tutorial.spring.model.User;
import com.bchen.tutorial.spring.model.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class FormController {
    @Autowired
    private UserValidator validator;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validator);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(format, false));
        binder.registerCustomEditor(String.class, "name", new UppercaseEditor());
        //for all String field:
        //binder.registerCustomEditor(String.class, new UppercaseNameEditor());
    }

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
        user.setLastLogin(new Date());
        model.addAttribute("user", user);
        return "autoUserForm";
    }

    @PostMapping("/auto-user-form")
    public String autoUserForm (@Valid @ModelAttribute("user") User user, BindingResult result,
                                Model model, SessionStatus status){
        //not necessary when there is @InitBinder
        //validator.validate(user, result);
        if(result.hasErrors()) {
            return "autoUserForm";
        }
        model.addAttribute("user", user);
        status.setComplete();

        return "autoUserLogged";
    }
}
