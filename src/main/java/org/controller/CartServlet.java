package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.dto.ProductCartDto;
import org.service.CartService;
import org.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartServlet extends HttpServlet {
    CartService cartService = new CartService();
    List<ProductCartDto> cartProducts = new ArrayList<>();
    String email;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //req.getServletPath()
        /*String action = req.getParameter("action");
        email = req.getParameter("email");
        System.out.println("email from cart ->"+email);
        if (action.equals("getInitialCart")){
            int cartId = cartService.getCartId(email);
            cartProducts = cartService.getAllCartProducts(cartId);
            for (ProductCartDto temp : cartProducts) {
                temp.setImageUrl("");
                System.out.println("Cart Servlet -> "+temp.toString());
            }
            Gson gson = new Gson();
            String json = gson.toJson(cartProducts);
            // Write the JSON string to the response
            System.out.println("the response json in cart servlet ->"+json);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }else if (action.equals("removeProduct")){
            //TODO -> delete first and then get the products in cart again to send it to the ajax cart.
            System.out.println("cart products in case of removing : ");
            int productId = Integer.parseInt(req.getParameter("productId"));
            //TODO -> delete product with productId from cart of user's email equal to predefined email.
            for (ProductCartDto tempProduct : cartProducts) {
                if (tempProduct.getProductId() == productId)
                    cartProducts.remove(tempProduct);
            }
            System.out.println("after removing the item -> "+cartProducts);
            Gson gson = new Gson();
            String json = gson.toJson(cartProducts);
            // Write the JSON string to the response
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }*/
        /*RequestDispatcher requestDispatcher = req.getRequestDispatcher("cart.jsp");
        requestDispatcher.forward(req,resp);*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //TODO-> firstly, we need to fetch the cart id.
        System.out.println("Cart get method in servlet");
        HttpSession session = req.getSession(false);
        //String email = (String) session.getAttribute(Constants.USER_EMAIL);
        String email = Constants.USER_EMAIL;
        int cartId = cartService.getCartId(email);
        List<ProductCartDto> cartProducts = cartService.getAllCartProducts(cartId);
        for (ProductCartDto temp : cartProducts) {
            System.out.println("Cart Servlet -> "+temp.toString());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("cart.jsp");
        req.setAttribute("cartProducts",cartProducts);
        requestDispatcher.forward(req,resp);
    }
}
