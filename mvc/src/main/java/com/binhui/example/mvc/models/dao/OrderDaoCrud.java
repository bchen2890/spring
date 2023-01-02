package com.binhui.example.mvc.models.dao;

import com.binhui.example.mvc.models.entity.Order;
import com.binhui.example.mvc.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderDaoCrud extends CrudRepository<Order, Long> {

}
