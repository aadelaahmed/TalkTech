package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.User;
import org.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;

public class SignInServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("Hello from talktech app");
        User user = new User();
        userService.saveUser(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }
}
