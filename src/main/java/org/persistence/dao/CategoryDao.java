package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.persistence.Database;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;

import java.util.List;

public class CategoryDao {
    EntityManager entityManager;
    public CategoryDao(){
        this.entityManager = JPAUtil.getEntityManager();
    }

    public List<Product> getProductsOnCategory(List<String> categories, List<String> brands) {
        entityManager.getTransaction().begin();
        return Database.doInTransaction(
                consumer -> {
                     CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    Predicate brandPredicate = rootProduct.get("brand").in(brands);
                    Predicate categoryPredicate = rootProduct.get("category").in(categories);
                    Predicate finalPredicate = null;
                    if (brands.size() != 0 && categories.size() !=0)
                    {
                         finalPredicate = criteriaBuilder.and(brandPredicate,categoryPredicate);
                    }else{
                         finalPredicate = criteriaBuilder.or(brandPredicate,categoryPredicate);
                    }

                    //Predicate finalPredicate = criteriaBuilder.or(brandPredicate,categoryPredicate);
                    queryProduct.select(rootProduct).where(
                        finalPredicate
                    );
                    List<Product> products = entityManager.createQuery(queryProduct).getResultList();
                    System.out.println("products size in dao ->"+products.size());
                    for (Product product:products ) {
                        System.out.println("product name is in daaaao ->"+product.getName());
                        Hibernate.initialize(product.getCartItems());
                        Hibernate.initialize(product.getOrderItems());
                    }
                    return products;
                }
        );
        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct = queryProduct.from(Product.class);
        Predicate brandPredicate = rootProduct.get("brand").in(brands);
        Predicate categoryPredicate = rootProduct.get("category").in(categories);
        Predicate finalPredicate = null;
        if (brands.size() != 0 && categories.size() !=0)
        {
             finalPredicate = criteriaBuilder.and(brandPredicate,categoryPredicate);
        }else{
             finalPredicate = criteriaBuilder.or(brandPredicate,categoryPredicate);
        }

        //Predicate finalPredicate = criteriaBuilder.or(brandPredicate,categoryPredicate);
        queryProduct.select(rootProduct).where(
            finalPredicate
        );
        List<Product> products = entityManager.createQuery(queryProduct).getResultList();
        System.out.println("products size in dao ->"+products.size());
        for (Product product:products ) {
            System.out.println("product name is in daaaao ->"+product.getName());
            Hibernate.initialize(product.getCartItems());
            Hibernate.initialize(product.getOrderItems());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return products;*/
    }

    public List<Product> getAllProducts() {
        System.out.println("hello from empty catetgory filter");
        return Database.doInTransaction(
                consumer -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    queryProduct.select(rootProduct);
                    List<Product> products = entityManager.createQuery(queryProduct).getResultList();
                    for (Product product:products ) {
                        System.out.println("product name is in daaaao ->"+product.getName());
                        Hibernate.initialize(product.getCartItems());
                        Hibernate.initialize(product.getOrderItems());
                    }
                    return products;
                }
        );
       /* entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct = queryProduct.from(Product.class);
        queryProduct.select(rootProduct);
        List<Product> products = entityManager.createQuery(queryProduct).getResultList();
        for (Product product:products ) {
            System.out.println("product name is in daaaao ->"+product.getName());
            Hibernate.initialize(product.getCartItems());
            Hibernate.initialize(product.getOrderItems());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return products;*/
    }
}
