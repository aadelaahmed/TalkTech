package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.persistence.Database;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;
import java.util.*;
import java.util.List;

public class ProductDao {
    public ProductDao(){
    }
    public void save(Product product){
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Product> getLimitedProducts(int limit){
        return Database.doInTransaction(
                paramEntityManager ->
                {
                    /*EntityManager entityManager = JPAUtil.getEntityManager();
                    entityManager.getTransaction().begin();*/
                    CriteriaBuilder criteriaBuilder = paramEntityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    queryProduct.select(rootProduct);
                    Query limitQuery = paramEntityManager.createQuery(queryProduct);
                    limitQuery.setMaxResults(limit);
                    List<Product> products = limitQuery.getResultList();
                    return products;
                }
        );
        /*EntityManager entityManager = JPAUtil.getEntityManager();
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
        return products;*/
    }

    public Product getProductById(int id) {
        return Database.doInTransaction(
                paramEntityManager ->
                {
                    /*EntityManager entityManager = JPAUtil.getEntityManager();
                    entityManager.getTransaction().begin();*/
                    CriteriaBuilder criteriaBuilder = paramEntityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    queryProduct.select(rootProduct);
                    Predicate condition = criteriaBuilder.equal(rootProduct.get("productId"), id);
                    queryProduct.where(condition);
                    Product resProduct = paramEntityManager.createQuery(queryProduct).getSingleResult();
                    return resProduct;
                }
        );
    }
}
