package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;
import java.util.*;
import java.util.List;

public class ProductDao {
    EntityManager entityManager;
    public ProductDao(){
        this.entityManager = JPAUtil.getEntityManager();
    }
    public void save(Product product){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Product> getLimitedProducts(int limit){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct = queryProduct.from(Product.class);
        queryProduct.select(rootProduct);
        Query limitQuery = entityManager.createQuery(queryProduct);
        limitQuery.setMaxResults(limit);
        List<Product> products = limitQuery.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return products;
    }
}
