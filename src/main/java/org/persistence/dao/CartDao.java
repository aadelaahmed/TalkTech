package org.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.dto.ProductCartDto;
import org.persistence.Database;
import org.persistence.JPAUtil;
import org.persistence.entities.Cart;
import org.persistence.entities.CartItems;
import org.persistence.entities.Product;
import org.persistence.entities.User;

import java.util.*;
public class CartDao{

    public CartDao(){

    }


    public void updateProduct(int quantity){

    }
    public void removeProduct(int productId){

    }

    public int getCartId(String email) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
                    Root<Cart> cart = cq.from(Cart.class);
                    Subquery<Integer> subquery = cq.subquery(Integer.class);
                    Root<User> userRoot = subquery.from(User.class);
                    subquery.select(userRoot.get("userId"))
                            .where(cb.equal(userRoot.get("email"), email));
                    cq.select(cart.get("cartId"))
                            .where(cb.equal(cart.get("userId"), subquery));
                    List<Integer> cartIds = entityManager.createQuery(cq).getResultList();
                    if (cartIds.isEmpty()) {

                        System.out.println("No cart found for the given email address.");
                        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                                .setParameter("email", email)
                                .getSingleResult();
                        Cart newCart = new Cart();
                        newCart.setUserId(user.getUserId());
                        newCart.setIsBought(false);
                        entityManager.persist(newCart);
                        entityManager.flush();
                        System.out.println("New cart created with ID: " + newCart.getCartId());
                        return newCart.getCartId();
                    } else {
                        System.out.println("Cart ID: " + cartIds.get(0));
                        return cartIds.get(0);
                    }
                }
        );
    }
    public List<ProductCartDto> getCartProducts(int cartId){
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<ProductCartDto> query = cb.createQuery(ProductCartDto.class);
                    Root<CartItems> cartItems = query.from(CartItems.class);
                    Join<CartItems, Product> product = cartItems.join("product");
                    query.select(cb.construct(ProductCartDto.class,
                            product.get("productId"),
                            product.get("name"),
                            product.get("brand"),
                            product.get("imageUrl"),
                            cartItems.get("quantity"),
                            product.get("quantity"),
                            product.get("price")));
                    query.where(cb.equal(cartItems.get("cart").get("cartId"), cartId));
                     List<ProductCartDto> results = entityManager.createQuery(query).getResultList();
                     return results;
                }
        );
    }
}
