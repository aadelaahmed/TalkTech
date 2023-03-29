package org.service;

import org.dto.ProductCartDto;
import org.persistence.dao.CheckoutDao;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutService {
    CheckoutDao checkoutDao;
    CartService cartService;
    public CheckoutService(){
        this.checkoutDao = new CheckoutDao();
        this.cartService = new CartService();
    }

    public BigDecimal checkoutOrder(int cartId) {
        return checkoutDao.getTotalPrice(cartId);
    }

    public BigDecimal getCreditLimitForUser(String email) {
        return checkoutDao.getCreditLimitForUser(email);
    }


    public List<ProductCartDto> getAllCartProducts(int cartId) {
        return cartService.getAllCartProducts(cartId);
    }

    public void updateProductsWithNewQuantity(List<ProductCartDto> cartProducts) {
        for (ProductCartDto cartProduct:cartProducts) {
            CheckoutDao tempDao = new CheckoutDao();
            System.out.println(cartProduct);
            int newQuantity = cartProduct.getQtyInStock() - cartProduct.getQtyInCart();
            System.out.println("new Quantity to be updated in product cart checked in checkout service ->"+newQuantity);
            tempDao.updateProductQty(cartProduct.getProductId(),newQuantity);
        }
    }

    public void updateCartAsBought(int cartId, BigDecimal totalPrice) {
        checkoutDao.updateCartAsBought(cartId,totalPrice);
    }

    public void updateCreditLimit(String email,BigDecimal newCreditLimit) {
        checkoutDao.updateCreditLimit(email,newCreditLimit);
    }
}
