package org.service;

import org.dto.ProductCartDto;
import org.persistence.dao.CartDao;

import java.math.BigDecimal;
import java.util.List;

public class CartService {
    CartDao cartDao;
    public CartService(){
        this.cartDao = new CartDao();
    }

    public List<ProductCartDto> getAllCartProducts(int cartId){
        return cartDao.getCartProducts(cartId);
    }
    public int getCartId(String email){
        return cartDao.getCartId(email);
    }
    public Boolean deleteProductFromCart(int cartId, int productId){
        return cartDao.deleteProductFromCart(cartId,productId);
    }

    public Boolean updateProductInCart(int cartId, int productId, int newQuantity ){
        return cartDao.updateProductQuantity(cartId,productId,newQuantity);
    }
    public Boolean addProductToCart(int cartId,int productId){
        return cartDao.addProductToCart(cartId,productId);
    }


}
