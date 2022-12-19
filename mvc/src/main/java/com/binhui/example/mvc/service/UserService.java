package com.binhui.example.mvc.service;

import com.binhui.example.mvc.models.dao.IUserDao;
import com.binhui.example.mvc.models.dao.UserDaoCrud;
import com.binhui.example.mvc.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService{
    /*
    @Autowired
    private IUserDao userDao;*/

    @Autowired
    @Qualifier("userDaoCrud")
    private UserDaoCrud userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findOne(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
