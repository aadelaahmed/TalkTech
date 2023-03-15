package org.persistence.dao;

import jakarta.persistence.EntityManager;
import org.persistence.JPAUtil;
import org.persistence.entities.Product;
import java.util.*;
public class CartDao{
    EntityManager entityManager;
    public CartDao(){
        entityManager = JPAUtil.getEntityManager();
    }
    public List<Product> getProducts(int userId){
        //firstly, get cart id and then use it with cart items.
        return new ArrayList<>();
    }

    public void updateProduct(int quantity){

    }
    public void removeProduct(int productId){

    }
}
