package com.binhui.example.mvc.controller;

import com.binhui.example.mvc.models.dao.IUserDao;
import com.binhui.example.mvc.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private IUserDao userDao;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userDao.findAll());
        return "list";
    }

    @RequestMapping(value="/form")
    public String create (Map<String, Object> model){
        User user = new User();
        model.put("user", user);
        return "form";
    }

    @PutMapping(value = "/form/{id}")
    public String edit(@PathVariable(value="id") Long id, Map<String, Object> model) {

        User user = null;

        if(id > 0) {
            user = userDao.findOne(id);
        } else {
            return "redirect:/list";
        }
        model.put("user", user);
        return "form";
    }

    @PostMapping("/user")
    public String save(@Validated User user, BindingResult result, Model model, SessionStatus status) {
        if(result.hasErrors()) {
            return "form";
        }

        userDao.save(user);
        status.setComplete();
        return "redirect:list";
    }

    @DeleteMapping(value="/user/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        if(id > 0) {
            userDao.delete(id);
        }
        return "redirect:/listar";
    }
}
