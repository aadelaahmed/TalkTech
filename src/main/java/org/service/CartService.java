package org.service;

import org.dto.ProductCartDto;
import org.persistence.dao.CartDao;

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
}
