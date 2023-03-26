package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.persistence.JPAUtil;
import org.persistence.entities.User;

public class UserDao {

    public User findById(Long id) {
        User user = JPAUtil.getEntityManager().find(User.class, id);
        return user;
    }

    public void save(User user) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User findByEmail(String email) {
        EntityManager entityManager = JPAUtil.getEntityManager(); // initialize EntityManager
        String jpql = "SELECT u FROM User u WHERE u.email=:email";
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

    public User updateUser(User user, String email) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        String jpql = "UPDATE User u SET u.name = :name, u.email = :email, u.password = :password, u.job = :job, u.creditLimit = :creditLimit, u.address = :address, u.interests = :interests WHERE u.email = :emailSt";
        Query query = entityManager.createQuery(jpql);
        entityManager.getTransaction().begin();
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        query.setParameter("job", user.getJob());
        query.setParameter("creditLimit", user.getCreditLimit());
        query.setParameter("address", user.getAddress());
        query.setParameter("interests", user.getInterests());
        query.setParameter("emailSt", email);
        int updatedCount = query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    public User findByEmilandPassword(String email, String password) {
        EntityManager entityManager = JPAUtil.getEntityManager(); // initialize EntityManager
        String jpql = "SELECT u FROM User u WHERE u.email = :email and u.password = :password";
        Query query = entityManager.createQuery(jpql);
        entityManager.getTransaction().begin();
        query.setParameter("email", email);
        query.setParameter("password",password);
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
