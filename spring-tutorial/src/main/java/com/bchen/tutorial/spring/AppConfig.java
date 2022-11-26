package com.bchen.tutorial.spring;

import java.util.Arrays;
import com.bchen.tutorial.spring.model.Order;
import com.bchen.tutorial.spring.model.OrderItem;
import com.bchen.tutorial.spring.model.Product;
import com.bchen.tutorial.spring.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean("initOrder")
    @Primary
    public Order initOrder(){
        User user = new User("Tom", 30);
        
        Product product1 = new Product("Apple", 1);
        Product product2 = new Product("Cookies", 1.2);

        OrderItem item1 = new OrderItem(product1, 2);
        OrderItem item2 = new OrderItem(product2, 1);

        Order order = new Order(user, Arrays.asList(item1, item2));

        return order;
    }

    @Bean("initOfficeOrder")
    public Order initOfficeOrder(){
        User user = new User("Tom", 30);

        Product product1 = new Product("Mouse", 30);
        Product product2 = new Product("Printer", 100);
        Product product3 = new Product("Desk", 200);
        Product product4 = new Product("Monitor", 300);
        Product product5 = new Product("Chair", 80);

        OrderItem item1 = new OrderItem(product1, 2);
        OrderItem item2 = new OrderItem(product2, 1);
        OrderItem item3 = new OrderItem(product3, 3);
        OrderItem item4 = new OrderItem(product4, 2);
        OrderItem item5 = new OrderItem(product5, 5);
        Order order = new Order(user, Arrays.asList(item1, item2, item3, item4, item5));
        return order;
    }

}
