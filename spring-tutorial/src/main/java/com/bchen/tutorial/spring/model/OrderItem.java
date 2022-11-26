package com.bchen.tutorial.spring.model;

public class OrderItem {
    private Product product;
    private Integer quantity;

    public OrderItem (Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
