package com.binhui.example.mvc.controller;

import com.binhui.example.mvc.models.entity.Order;
import com.binhui.example.mvc.models.entity.OrderItem;
import com.binhui.example.mvc.models.entity.Product;
import com.binhui.example.mvc.models.entity.User;
import com.binhui.example.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {

    @Autowired
    private IUserService userService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/form/{userId}")
    public String create(@PathVariable(value = "userId") Long userId, Map<String, Object> model,
                        RedirectAttributes flash) {

        User user = userService.findOne(userId);

        if (user == null) {
            flash.addFlashAttribute("error", "This user doesn't exist");
            return "redirect:/list";
        }

        Order order = new Order();
        order.setUser(user);

        model.put("order", order);
        return "order/form";
    }

    @GetMapping(value = "/load-products/{term}", produces = { "application/json" })
    public @ResponseBody
    List<Product> loadProducts(@PathVariable String term) {
        return userService.findByName(term);
    }

    @PostMapping("/form")
    public String save(Order order,
                          @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                          @RequestParam(name = "quantity[]", required = false) Integer[] quantity,
                          RedirectAttributes flash,
                          SessionStatus status) {

        for (int i = 0; i < itemId.length; i++) {
            Product product = userService.findProductById(itemId[i]);

            OrderItem linea = new OrderItem();
            linea.setQuantity(quantity[i]);
            linea.setProduct(product);
            order.addOrderItem(linea);

            log.info("ID: " + itemId[i].toString() + ", quantity: " + quantity[i].toString());
        }

        userService.saveOrder(order);
        status.setComplete();
        flash.addFlashAttribute("success", "Order created successfully");
        return "redirect:/view/" + order.getUser().getId();
    }

}