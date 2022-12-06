package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Role;

import java.util.List;

public interface IRoleService {
    public List<Role>getList();
    public Role getById(Integer id);
}
