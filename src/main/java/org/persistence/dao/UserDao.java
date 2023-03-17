package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
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
        try {
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public User findByEmail(String email) {
        EntityManager entityManager = JPAUtil.getEntityManager(); // initialize EntityManager
        String jpql = "SELECT u FROM User u Where u.email= :email";
        Query query = entityManager.createQuery(jpql);
        entityManager.getTransaction().begin();
        query.setParameter("email", email);
        try {
            User user = (User) query.getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return user;
        } catch (NoResultException e) {
            e.printStackTrace();
            entityManager.getTransaction().commit();
            entityManager.close();
            return null;
        }
    }

}
