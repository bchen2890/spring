package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.model.*;
import com.bchen.tutorial.spring.service.ICountryService;
import com.bchen.tutorial.spring.service.IRoleService;
import com.bchen.tutorial.spring.service.RoleService;
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
import java.util.*;

@Controller
@SessionAttributes("user")
public class FormController {
    @Autowired
    private UserValidator validator;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private CountryEditor countryEditor;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private RolesEditor rolesEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(validator);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(format, false));
        binder.registerCustomEditor(String.class, "name", new UppercaseEditor());
        //for all String field:
        //binder.registerCustomEditor(String.class, new UppercaseNameEditor());

        binder.registerCustomEditor(Country.class, "country", countryEditor);
        binder.registerCustomEditor(Role.class, "roles", rolesEditor);

    }

    @ModelAttribute("rolesList")
    public List<Role> rolesList(){
        return this.roleService.getList();
    }

    @ModelAttribute("countries")
    public List<String> countries(){
        return Arrays.asList("Spain","Portugal", "Andorra", "France", "Germany", "Italy");
    }
    @ModelAttribute("countriesMap")
    public Map<String, String> countriesMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("ES", "Spain");
        map.put("PT", "Portugal");
        map.put("AD", "Andorra");
        map.put("FR", "France");
        map.put("DE", "Germany");
        map.put("IT", "Italy");
        return map;
    }
    @ModelAttribute("countriesList")
    public List<Country> countriesList(){
        return Arrays.asList(new Country(1, "ES", "Spain"),
                new Country(2, "PT", "Portugal"),
                new Country(3, "AD", "Andorra"),
                new Country(4, "FR", "France"),
                new Country(5, "DE", "Germany"),
                new Country(6, "IT", "Italy"));
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
