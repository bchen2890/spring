package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.error.RoleNotFoundException;
import com.bchen.tutorial.spring.model.Role;
import com.bchen.tutorial.spring.model.RolesEditor;
import com.bchen.tutorial.spring.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private RolesEditor rolesEditor;


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

    @GetMapping("number-format-error")
    public String numberFormatError(Model model){
        Integer i = Integer.parseInt("hello");
        return "error/404";
    }
    @GetMapping("view/role/{id}")
    public String viewRole(@PathVariable Integer id, Model model){
        Role role = roleService.getByIdOptional(id).orElseThrow(()-> new RoleNotFoundException(id.toString()));
        model.addAttribute("role", role);
        return "viewRole";
    }

}
