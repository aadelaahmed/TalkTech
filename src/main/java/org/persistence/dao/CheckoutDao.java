package org.persistence.dao;

import jakarta.persistence.criteria.*;
import org.persistence.Database;
import org.persistence.entities.Cart;
import org.persistence.entities.CartItems;
import org.persistence.entities.Product;
import org.persistence.entities.User;

import java.math.BigDecimal;

import static org.persistence.Database.doInTransaction;

public class CheckoutDao {
    CartDao cartDao;
    public CheckoutDao(){
        this.cartDao = new CartDao();
    }

    public int getCartId(String email) {
        return cartDao.getCartId(email);
    }

    public BigDecimal getTotalPrice(int cartId){
        return doInTransaction(
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
        return doInTransaction(
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


    public void updateProductQty(Integer productId, int newQuantity) {
         Database.doInTransactionWithoutResult(
                entityManager -> {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaUpdate<Product> update = cb.createCriteriaUpdate(Product.class);
                    Root<Product> productRoot = update.from(Product.class);
                    update.set(productRoot.get("quantity"), newQuantity);
                    update.where(cb.equal(productRoot.get("productId"), productId));
                    entityManager.createQuery(update).executeUpdate();
                    System.out.println("product quantity is updated");
                }
        );
    }

    public void updateCartAsBought(int cartId, BigDecimal totalPrice) {
        Database.doInTransactionWithoutResult(
                entityManager -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaUpdate<Cart> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Cart.class);
                    Root<Cart> cartRoot = criteriaUpdate.from(Cart.class);
                    criteriaUpdate.set(cartRoot.get("isBought"), true);
                    criteriaUpdate.set(cartRoot.get("totalPrice"), totalPrice);
                    criteriaUpdate.where(criteriaBuilder.equal(cartRoot.get("cartId"), cartId));
                    //int rowsUpdated = entityManager.createQuery(criteriaUpdate).executeUpdate();
                    entityManager.createQuery(criteriaUpdate).executeUpdate();
                    System.out.println("the cartId's isBought attribute is updated ,cartid-> "+cartId);

                }
        );
    }

    public void updateCreditLimit(String email,BigDecimal newCreditLimit) {
        Database.doInTransactionWithoutResult(
                entityManager -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
                    Root<User> userRoot = criteriaUpdate.from(User.class);
                    criteriaUpdate.set(userRoot.get("creditLimit"), newCreditLimit);
                    criteriaUpdate.where(criteriaBuilder.equal(userRoot.get("email"), email));
//                    int rowsUpdated = entityManager.createQuery(criteriaUpdate).executeUpdate();
                    entityManager.createQuery(criteriaUpdate).executeUpdate();
                    System.out.println("the credit limit of user with email -> "+email+" is updated");
                }
        );
    }
}
