package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Role;
import com.bchen.tutorial.spring.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    private List<Role> list;
    public RoleService(){
        this.list = Arrays.asList(
                new Role(1, "Administrator", "ROLE_ADMIN"),
                new Role(2, "User", "ROLE_USER"),
                new Role(3, "Coordinator", "ROLE_COORDINATOR"));
    }

    @Override
    public List<Role> getList() {
        return list;
    }

    @Override
    public Role getById(Integer id) {
        for (Role role : list) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public Optional<Role> getByIdOptional(Integer id) {
        Role role = this.getById(id);
        return Optional.ofNullable(role);
    }
}
