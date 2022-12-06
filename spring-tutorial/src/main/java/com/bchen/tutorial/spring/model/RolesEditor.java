package com.bchen.tutorial.spring.model;

import com.bchen.tutorial.spring.service.IRoleService;
import com.bchen.tutorial.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RolesEditor extends PropertyEditorSupport {

    @Autowired
    private IRoleService service;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(text);
            setValue(service.getById(id));
        } catch(NumberFormatException e) {
            setValue(null);
        }
    }

}
