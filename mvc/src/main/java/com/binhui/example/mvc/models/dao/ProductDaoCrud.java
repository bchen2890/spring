package com.binhui.example.mvc.models.dao;

import com.binhui.example.mvc.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDaoCrud extends CrudRepository<Product, Long> {
    public List<Product> findByNameLikeIgnoreCase(String term);
}
