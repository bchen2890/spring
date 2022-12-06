package com.bchen.tutorial.spring.service;

import com.bchen.tutorial.spring.model.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryService implements ICountryService{
    private List<Country> list;
    public CountryService(){
        this.list = Arrays.asList(new Country(1, "ES", "Spain"),
                new Country(2, "PT", "Portugal"),
                new Country(3, "AD", "Andorra"),
                new Country(4, "FR", "France"),
                new Country(5, "DE", "Germany"),
                new Country(6, "IT", "Italy"));
    }

    @Override
    public List<Country> getList() {
        return list;
    }

    @Override
    public Country getById(Integer id) {
        for (Country country : list) {
            if (country.getId().equals(id)) {
                return country;
            }
        }
        return null;
    }
}
