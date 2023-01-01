package com.binhui.example.mvc.models.dao;

import com.binhui.example.mvc.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component("userDaoCrud")
public interface UserDaoCrud extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable page);
}
