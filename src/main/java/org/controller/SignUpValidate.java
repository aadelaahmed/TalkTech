package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.User;
import org.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;

public class SignUpValidate extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        System.out.println("email: "+ email);
        UserService userService = new UserService();
        if(email!=""){
        User user = userService.findByEmail(email);
        if(user!=null) {
            String user_email = user.getEmail();
            System.out.println(user_email + "user_email");
            out.println("Already Exists");
        }
            else{
                out.println("Email Is Available");
            }
        }
    }

}
