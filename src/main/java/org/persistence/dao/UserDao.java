package org.persistence.dao;

import jakarta.persistence.EntityManager;
import org.persistence.JPAUtil;
import org.persistence.entities.User;

public class UserDao {

    public User findById(Long id){
        User user = JPAUtil.getEntityManager().find(User.class,id);
        return user;
    }

    public void save(User user){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

}
