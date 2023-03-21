package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;

import java.util.List;

public class CategoryDao {
    EntityManager entityManager;
    public CategoryDao(){
        this.entityManager = JPAUtil.getEntityManager();
    }

    public List<Product> getProductsOnCategory(List<Integer> categoryIds, List<String> brands) {
        //entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct = queryProduct.from(Product.class);
        Predicate brandPredicate = rootProduct.get("brand").in(brands);
        Predicate categoryPredicate = rootProduct.get("categoryId").in(categoryIds);
        Predicate finalPredicate = criteriaBuilder.and(brandPredicate,categoryPredicate);
        queryProduct.select(rootProduct).where(
            finalPredicate
        );
        List<Product> products = entityManager.createQuery(queryProduct).getResultList();
        return products;
    }
}
