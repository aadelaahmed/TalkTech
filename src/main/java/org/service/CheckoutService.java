package org.service;

import org.persistence.dao.CartDao;
import org.persistence.dao.CheckoutDao;

import java.math.BigDecimal;

public class CheckoutService {
    CheckoutDao checkoutDao;
    public CheckoutService(){
        this.checkoutDao = new CheckoutDao();
    }

    public BigDecimal checkoutOrder(String email) {
        int cartId = checkoutDao.getCartId(email);
        return checkoutDao.getTotalPrice(cartId);
    }

    public BigDecimal getCreditLimitForUser(String email) {
        return checkoutDao.getCreditLimitForUser(email);
    }
}
