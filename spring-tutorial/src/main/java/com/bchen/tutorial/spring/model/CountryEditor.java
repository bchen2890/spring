package com.bchen.tutorial.spring.model;

import com.bchen.tutorial.spring.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CountryEditor extends PropertyEditorSupport {
    @Autowired
    private ICountryService service;
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text != null && text.length()>0){
            try {
                Integer id = Integer.parseInt(text);
                this.setValue(service.getById(id));
            } catch (NumberFormatException e){
                this.setValue(null);
            }
        } else {
            this.setValue(null);
        }
    }
}
