package com.binhui.example.mvc.controller;

import com.binhui.example.mvc.models.entity.User;
import com.binhui.example.mvc.service.IUserService;
import com.binhui.example.mvc.service.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private IUserService userService;

   /* @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "list";
    }*/

    @GetMapping("/list")
    public String list(@RequestParam(name="page", defaultValue="0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 8);

        Page<User> users = userService.findAll(pageRequest);

        PageRender<User> pageRender = new PageRender<User>("/list", users);
        model.addAttribute("users", users);
        model.addAttribute("page", pageRender);
        return "list";
    }

    @RequestMapping(value="/form")
    public String create (Map<String, Object> model){
        User user = new User();
        user.setCreatedAt(new Date());
        model.put("user", user);
        return "form";
    }

    @RequestMapping(value = "/user/{id}")
    public String edit(@PathVariable(value="id") Long id, Map<String, Object> model) {

        User user = null;

        if(id > 0) {
            user = userService.findOne(id);
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

        userService.save(user);
        status.setComplete();
        return "redirect:/list";
    }

    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        if(id > 0) {
            userService.delete(id);
        }
        return "redirect:/list";
    }
}
