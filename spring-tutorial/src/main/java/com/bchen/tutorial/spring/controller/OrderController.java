package com.bchen.tutorial.spring.controller;

import com.bchen.tutorial.spring.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    @Qualifier("officeOrderService")
    IOrderService officeOrderService;

    @GetMapping("/{name}/")
    public String order(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("order", orderService.getUserOrder());
        return "order";
    }

    @GetMapping("/{name}/office")
    public String office(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("order", officeOrderService.getUserOrder());
        return "order";
    }
}
