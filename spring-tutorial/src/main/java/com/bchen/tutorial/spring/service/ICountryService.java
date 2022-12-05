package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Country;

import java.util.List;

public interface ICountryService {
    public List<Country>getList();
    public Country getById(Integer id);
}
