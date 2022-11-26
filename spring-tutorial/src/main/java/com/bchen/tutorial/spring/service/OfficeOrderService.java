package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("officeOrderService")
public class OfficeOrderService implements IOrderService{
    @Autowired
    @Qualifier("initOfficeOrder")
    private Order order;

    @Override
    public Order getUserOrder() {
        return order;
    }
}
