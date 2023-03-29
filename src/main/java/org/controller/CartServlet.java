package org.controller;

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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartServlet extends HttpServlet {
    CartService cartService = new CartService();
    List<ProductCartDto> cartProducts = new ArrayList<>();
    String email;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mappingUrl = req.getServletPath();
        PrintWriter printWriter;
        email = Constants.USER_EMAIL;
        if (mappingUrl.equals("/"+Constants.DELETE_CART_URL_MAPPING)){
            deleteProductFromCart(req,resp);
        }else if (mappingUrl.equals("/"+Constants.UPDATE_CART_URL_MAPPING)){
            updateProductInCart(req);
        }else if (mappingUrl.equals("/"+Constants.ADD_CART_URL_MAPPING)){
            addProductToCart(req,resp);
        }
    }



    private void addProductToCart(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //TODO -> get email of user from session.
        System.out.println("start adding product to cart");
        int productId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession(false);
        String looggedIn = (String) session.getAttribute(Constants.LOGGED_IN_SESSION_ATTR);
        Integer cartId = (int) session.getAttribute(Constants.CART_ID_SESSION_ATTR);
        cartService.addProductToCart(cartId,productId);
        resp.sendRedirect("showCart");
        /*if (looggedIn != null){
            System.out.println("cart id in cart servlet is -> "+cartId);
        }
        else
        {
            System.out.println("user not login yet in add to cart servlet");
            resp.sendRedirect("login.jsp");
        }*/
    }

    private void updateProductInCart(HttpServletRequest req) {
        System.out.println("checking the cart product previously -> "+cartProducts);
        System.out.println("start updating the quantity");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        //TODO -> get the cart id from session.
        HttpSession session = req.getSession(false);
        int cartId = (int) session.getAttribute(Constants.CART_ID_SESSION_ATTR);
        Boolean isUpdated = cartService.updateProductInCart(cartId,productId,quantity);
        if (isUpdated == null || !isUpdated){
            //TODO -> show dialog this product was purchased.
        }else{
            //TODO -> deleted successfully,create new dto for updating price and total qty.
        }
    }

    private void deleteProductFromCart(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        System.out.println("Start deleting cart item in db");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("success deleting message from servlet");
        //TODO -> get the cart id from session.
        HttpSession session = req.getSession(false);
        int cartId = (int) session.getAttribute(Constants.CART_ID_SESSION_ATTR);
        int currentProductId = Integer.parseInt(req.getParameter("productId"));
        Boolean isDeleted = cartService.deleteProductFromCart(cartId,currentProductId);
        System.out.println("checking deletion of product -> "+isDeleted);
        System.out.println("checking the cart product previously -> "+cartProducts);
        if (isDeleted == null || !isDeleted){
            //TODO -> show dialog this product was purchased.
        }else{
            //TODO -> deleted successfully,create new dto for updating price and total qty.
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //TODO-> firstly, we need to fetch the cart id.
        String mappingUrl = req.getServletPath();
        System.out.println(mappingUrl);
        if (mappingUrl.equals("/"+Constants.SHOW_CART_URL_MAPPING)){
            System.out.println("Cart get method in servlet");
//            String email = (String) session.getAttribute(Constants.USER_EMAIL);
            HttpSession session = req.getSession(false);
            int cartId = (int) session.getAttribute(Constants.CART_ID_SESSION_ATTR);
            //TODO -> set cart id in session scope
            cartProducts = cartService.getAllCartProducts(cartId);
            for (ProductCartDto temp : cartProducts) {
                //temp.setImageUrl("");
                System.out.println("Cart Servlet -> "+temp.toString());
            }
//        Gson gson = new Gson();
//        String cartProductsJson = gson.toJson(cartProducts);
//        System.out.println("Json in servlet is -> "+cartProductsJson);
            System.out.println("open the cart jsp");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("cart.jsp");
            req.setAttribute("cartProducts",cartProducts);
            requestDispatcher.forward(req,resp);
        }
    }
}
