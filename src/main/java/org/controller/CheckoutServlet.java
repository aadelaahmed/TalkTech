package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.dto.ProductCartDto;
import org.service.CartService;
import org.service.CheckoutService;
import org.util.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class CheckoutServlet extends HttpServlet {
    CheckoutService checkoutService = new CheckoutService();
    CartService cartService = new CartService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        checkoutOrder(resp,req);
    }

    private void checkoutOrder(HttpServletResponse response,HttpServletRequest req) throws IOException {
        //TODO -> get the email from session and then send it to the checkout order
        // to get the cart id from this email which is not yet bought and then pass it to the cart dao for checkout.
        HttpSession session = req.getSession(false);
        int cartId = (int) session.getAttribute(Constants.CART_ID_SESSION_ATTR);
        String email = (String) session.getAttribute(Constants.EMAIL_SESSION_ATTR);
        BigDecimal totalPrice = checkoutService.checkoutOrder(cartId);
        BigDecimal creditLimit = checkoutService.getCreditLimitForUser(email);
        System.out.println("Total Price in servlet ---> "+totalPrice);
        System.out.println("creditLimit in servlet ---> "+creditLimit);
        PrintWriter printWriter = response.getWriter();
        if (creditLimit.compareTo(totalPrice) >= 0){
            List<ProductCartDto> cartProducts = checkoutService.getAllCartProducts(cartId);
            BigDecimal newCreditLimit = creditLimit.subtract(totalPrice);
            // TODO update the items and isBought column
            checkoutService.updateCartAsBought(cartId,totalPrice);
            // TODO update the creditlimit,
            checkoutService.updateCreditLimit(email,newCreditLimit);
            //  TODO update product's qunatity
            checkoutService.updateProductsWithNewQuantity(cartProducts);
            // TODO Navigate the customer to home page.
            // TODO pop up successfull message
            printWriter.write("Your Order is placed successfully");
        }else{
            //TODO -> show message that the credit limit is exceed
            printWriter.write("Your Credit Limit is less than total price of the order");
        }
    }
}
