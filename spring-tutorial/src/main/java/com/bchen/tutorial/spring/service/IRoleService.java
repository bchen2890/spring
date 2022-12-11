package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    public List<Role>getList();
    public Role getById(Integer id);
    public Optional<Role> getByIdOptional(Integer id);
}
