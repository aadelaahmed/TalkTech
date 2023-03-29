package org.persistence.dao;

import jakarta.persistence.criteria.*;
import org.persistence.Database;
import org.persistence.entities.Cart;
import org.persistence.entities.CartItems;
import org.persistence.entities.Product;
import org.persistence.entities.User;

import java.math.BigDecimal;

public class CheckoutDao {
    CartDao cartDao;
    public CheckoutDao(){
        this.cartDao = new CartDao();
    }

    public int getCartId(String email) {
        return cartDao.getCartId(email);
    }

    public BigDecimal getTotalPrice(int cartId){
        return Database.doInTransaction(
                entityManager -> {
                    // Calculate the total price of the cart
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<BigDecimal> totalPriceQuery = cb.createQuery(BigDecimal.class);
                    Root<CartItems> cartItems = totalPriceQuery.from(CartItems.class);
                    Join<CartItems, Product> product = cartItems.join("product", JoinType.INNER);
                    Join<CartItems, Cart> cart = cartItems.join("cart", JoinType.INNER);
                    totalPriceQuery.select(cb.sum(cb.prod(cartItems.get("quantity"), product.get("price"))))
                            .where(
                                    cb.and(
                                            cb.equal(cart.get("cartId"), cartId),
                                            cb.equal(cart.get("isBought"), false)
                                    )
                            );
                    BigDecimal totalPrice = entityManager.createQuery(totalPriceQuery).getSingleResult();
                    System.out.println("Total price of cart: " + totalPrice);
                    return totalPrice;
                }
        );
    }

    public BigDecimal getCreditLimitForUser(String email) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<BigDecimal> creditLimitQuery = cb.createQuery(BigDecimal.class);
                    Root<User> user = creditLimitQuery.from(User.class);
                    creditLimitQuery.select(user.get("creditLimit"))
                            .where(cb.equal(user.get("email"), email));
                    BigDecimal creditLimit = entityManager.createQuery(creditLimitQuery).getSingleResult();
                    System.out.println("Credit limit for user with email " + email + ": " + creditLimit);
                    return creditLimit;
                }
        );
    }
}
