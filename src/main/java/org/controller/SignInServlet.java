package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.persistence.entities.User;
import org.service.CartService;
import org.service.UserService;
import org.util.Constants;

import java.io.IOException;
import java.io.PrintWriter;

public class SignInServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        System.out.println("Inside Sign In Do Post Method");


        // Get the email value from the JSONObject
        String email = req.getParameter("email");
        System.out.println("email from form " + email);
        String password = req.getParameter("password");
        System.out.println("password from form " + password);
        UserService userService = new UserService();

        User user = userService.findByEmilandPassword(email, password);


        if (user != null) {
            CartService cartService = new CartService();
            HttpSession session = req.getSession(true);
            session.setAttribute("email", email);
            session.setAttribute("userName", user.getName());
            session.setAttribute("LoggedIn", "true");
            session.setAttribute("userType", user.getUserType());
            int cartId = cartService.getCartId(email);
            session.setAttribute(Constants.CART_ID_SESSION_ATTR,cartId);
            System.out.println("Logged in");
            System.out.println(user.getUserType());
            if (user.getUserType().trim().equals("admin")) {
                System.out.println("admin is true");
                printWriter.println("admin");
            } else {
                printWriter.println("Customer");
            }
        } else {
            printWriter.println("Email or password are not valid.");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }
}

