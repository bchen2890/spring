package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("orderService")
public class OrderService implements IOrderService{
    @Autowired
    private Order order;

    @Override
    public Order getUserOrder() {
        return order;
    }
}
