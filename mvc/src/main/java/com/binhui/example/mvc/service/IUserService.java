package com.binhui.example.mvc.service;

import com.binhui.example.mvc.models.entity.Order;
import com.binhui.example.mvc.models.entity.Product;
import com.binhui.example.mvc.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {
    public List<User> findAll();

    public void save(User user);

    public User findOne(Long id);

    public void delete(Long id);

    public Page<User> findAll(Pageable pageable);

    List<Product> findByName(String term);

    public void saveOrder(Order order);

    public Product findProductById(Long id);

    Order findOrderById(Long id);

    public void deleteOrder(Long id);

    public void saveProduct(Product product);
}