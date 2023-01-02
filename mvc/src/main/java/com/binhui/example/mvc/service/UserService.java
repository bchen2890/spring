package com.binhui.example.mvc.service;

import com.binhui.example.mvc.models.dao.OrderDaoCrud;
import com.binhui.example.mvc.models.dao.ProductDaoCrud;
import com.binhui.example.mvc.models.dao.UserDaoCrud;
import com.binhui.example.mvc.models.entity.Order;
import com.binhui.example.mvc.models.entity.Product;
import com.binhui.example.mvc.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private OrderDaoCrud orderDao;
    @Autowired
    private ProductDaoCrud productDao;

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

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByName(String term) {
        return productDao.findByNameLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public void saveOrder(Order order){
        orderDao.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id){
        return productDao.findById(id).orElse(null);
    }


}
