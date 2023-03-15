package org.persistence.dao;

import jakarta.persistence.EntityManager;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;

public class ProductDao {
    EntityManager entityManager;
    public ProductDao(){
        this.entityManager = JPAUtil.getEntityManager();
    }
    public void save(Product product){
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }
}
