package org.persistence.dao;

import jakarta.persistence.criteria.*;
import org.dto.ProductCartDto;
import org.persistence.Database;
import org.persistence.entities.*;

import java.math.BigDecimal;
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

    public Boolean deleteProductFromCart(int cartId, int productId) {
        return Database.doInTransaction(entityManager -> {
            boolean isDeleted = false;
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<CartItems> deleteQuery = cb.createCriteriaDelete(CartItems.class);
            Root<CartItems> root = deleteQuery.from(CartItems.class);
            deleteQuery.where(
                    cb.and(
                            cb.equal(root.get("cart").get("cartId"), cartId),
                            cb.equal(root.get("product").get("productId"), productId)
                    )
            );
            int numDeleted = entityManager.createQuery(deleteQuery).executeUpdate();
            if (numDeleted > 0) {
                isDeleted = true;
            }else {
                isDeleted = false;
            }
            System.out.println(numDeleted + " product(s) deleted from cart.");
            return isDeleted;
        });
    }

    public Boolean updateProductQuantity(int cartId, int productId, int newQuantity) {
        return Database.doInTransaction(entityManager -> {
            boolean isUpdated;
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<CartItems> updateQuery = cb.createCriteriaUpdate(CartItems.class);
            Root<CartItems> root = updateQuery.from(CartItems.class);
            updateQuery.set(root.get("quantity"), newQuantity);
            updateQuery.where(
                    cb.and(
                            cb.equal(root.get("cart").get("cartId"), cartId),
                            cb.equal(root.get("product").get("productId"), productId)
                    )
            );
            int numUpdated = entityManager.createQuery(updateQuery).executeUpdate();
            if (numUpdated > 0) {
                isUpdated = true;
            }else {
                isUpdated = false;
            }
            System.out.println(numUpdated + " product(s) updated in cart.");
            return isUpdated;
        });
    }

    public Boolean addProductToCart(int cartId, int productId) {
        return Database.doInTransaction(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CartItems> query = cb.createQuery(CartItems.class);
            Root<CartItems> root = query.from(CartItems.class);
            query.where(
                    cb.and(
                            cb.equal(root.get("cart").get("cartId"), cartId),
                            cb.equal(root.get("product").get("productId"), productId)
                    )
            );
            List<CartItems> items = entityManager.createQuery(query).getResultList();
            if (!items.isEmpty()) {
                // The product already exists in the cart, increase its quantity by 1
                CartItems item = items.get(0);
                item.setQuantity(item.getQuantity() + 1);
                entityManager.persist(item);
                System.out.println("Product quantity increased in cart.");
            } else {
                // The product does not exist in the cart, create a new CartItem entity
                CartItems item = new CartItems();
                System.out.println("before add check on card id -> "+cartId);
                System.out.println("before add check on productId -> "+productId);
                CartItemsId cartItemsId = new CartItemsId(cartId,productId);
                item.setId(cartItemsId);
                item.setCart(entityManager.getReference(Cart.class, cartId));
                item.setProduct(entityManager.getReference(Product.class, productId));
                item.setQuantity(1);
                entityManager.persist(item);
                System.out.println("Product added to cart.");
            }
            return true;
        });
    }



}
