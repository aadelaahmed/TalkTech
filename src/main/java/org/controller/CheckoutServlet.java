package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.service.CheckoutService;
import org.util.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class CheckoutServlet extends HttpServlet {
    CheckoutService checkoutService = new CheckoutService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        checkoutOrder(resp);
    }

    private void checkoutOrder(HttpServletResponse response) throws IOException {
        //TODO -> get the email from session and then send it to the checkout order
        // to get the cart id from this email which is not yet bought and then pass it to the cart dao for checkout.
        String email = Constants.USER_EMAIL;
        BigDecimal totalPrice = checkoutService.checkoutOrder(email);
        BigDecimal creditLimit = checkoutService.getCreditLimitForUser(email);
        System.out.println("Total Price in servlet ---> "+totalPrice);
        System.out.println("creditLimit in servlet ---> "+creditLimit);
        PrintWriter printWriter = response.getWriter();

        if (creditLimit.compareTo(totalPrice) >= 0){
            //TODO -> success checkout then update the items and isBought column
            printWriter.write("Your Order is checked successfully");
        }else{
            //TODO -> show message that the credit limit is exceed
            printWriter.write("Your Credit Limit is less than total price of the order");
        }
    }
}
