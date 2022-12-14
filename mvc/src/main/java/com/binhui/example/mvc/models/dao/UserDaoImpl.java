package com.binhui.example.mvc.models.dao;

import com.binhui.example.mvc.models.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao{

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return em.createQuery("from client").getResultList();
    }

    @Transactional
    @Override
    public void save(User user) {
        if(user.getId()!=null && user.getId() > 0){
            em.merge(user);
        }else{
            em.persist(user);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
