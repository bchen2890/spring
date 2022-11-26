package com.bchen.tutorial.spring.model;

import java.util.List;

public class Order {
    private User client;
    private List<OrderItem> items;

    public Order(User client, List<OrderItem> items){
        this.client = client;
        this.items = items;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

}
