package com.binhui.example.mvc.models.dao;

import com.binhui.example.mvc.models.entity.User;

import java.util.List;

public interface IUserDao {
    public List<User> findAll();
    public void save(User user);
    public User findOne(Long id);
    public void delete(Long id);
}
