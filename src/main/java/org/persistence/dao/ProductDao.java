package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.persistence.Database;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;
import java.util.*;
import java.util.List;

public class ProductDao {
    public ProductDao() {
    }

    public void save(Product product) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Product> getLimitedProducts(int limit) {
        return Database.doInTransaction(
                paramEntityManager -> {
                    /*
                     * EntityManager entityManager = JPAUtil.getEntityManager();
                     * entityManager.getTransaction().begin();
                     */
                    CriteriaBuilder criteriaBuilder = paramEntityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    queryProduct.select(rootProduct).where(criteriaBuilder.greaterThan(rootProduct.get("quantity"), 0));                    Query limitQuery = paramEntityManager.createQuery(queryProduct);
                    limitQuery.setMaxResults(limit);
                    List<Product> products = limitQuery.getResultList();
                    return products;
                });
    }

    public Product getProductById(int id) {
        return Database.doInTransaction(
                paramEntityManager -> {
                    CriteriaBuilder criteriaBuilder = paramEntityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    queryProduct.select(rootProduct);
                    Predicate condition = criteriaBuilder.and(
                            criteriaBuilder.equal(rootProduct.get("productId"), id),
                            criteriaBuilder.greaterThan(rootProduct.get("quantity"), 0)
                    );
                    queryProduct.where(condition);
                    Product resProduct = paramEntityManager.createQuery(queryProduct).getSingleResult();
                    return resProduct;
                });
    }

    public List<Product> getAllProducts() {
        return Database.doInTransaction(
                paramEntityManager -> {
                    CriteriaBuilder criteriaBuilder = paramEntityManager.getCriteriaBuilder();
                    CriteriaQuery<Product> queryProduct = criteriaBuilder.createQuery(Product.class);
                    Root<Product> rootProduct = queryProduct.from(Product.class);
                    queryProduct.select(rootProduct);
                    Query query = paramEntityManager.createQuery(queryProduct);
                    List<Product> products = query.getResultList();
                    return products;
                });
    }

    public void updateProduct(Product product) {
        // get the EntityManager instance
        EntityManager entityManager = JPAUtil.getEntityManager();

        // start a transaction
        entityManager.getTransaction().begin();

        // create the JPQL query to update the product
        String jpql = "UPDATE Product p SET p.name = :name, p.description = :description, "
                + "p.brand = :brand, p.price = :price, p.quantity = :quantity, "
                + "p.category = :category, p.color = :color WHERE p.productId = :productId";

        // create the query and set the parameters
        Query query = entityManager.createQuery(jpql);
        query.setParameter("name", product.getName());
        query.setParameter("description", product.getDescription());
        query.setParameter("brand", product.getBrand());
        query.setParameter("price", product.getPrice());
        query.setParameter("quantity", product.getQuantity());
        query.setParameter("category", product.getCategory());
        query.setParameter("color", product.getColor());
        query.setParameter("productId", product.getProductId());
        try {
            // execute the update query
            query.executeUpdate();

            // commit the transaction
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.close();
    }

    public void deleteProductById(Integer productId) {
        Database.doInTransactionWithoutResult(paramEntityManager -> {
//            CriteriaBuilder builder = paramEntityManager.getCriteriaBuilder();
//            CriteriaDelete<Product> delete = builder.createCriteriaDelete(Product.class);
//            Root<Product> root = delete.from(Product.class);
//            delete.where(builder.equal(root.get("productId"), productId));
//            paramEntityManager.createQuery(delete).executeUpdate();
            Product product = paramEntityManager.find(Product.class, productId);
            if (product != null) {
                // delete all related cart items
                Query cartItemQuery = paramEntityManager.createQuery("DELETE FROM CartItems c WHERE c.product = :product");
                cartItemQuery.setParameter("product", product);
                cartItemQuery.executeUpdate();

                // delete the product
                paramEntityManager.remove(product);
            }
        });
    }

}
