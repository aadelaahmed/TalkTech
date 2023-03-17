package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.User;
import org.service.UserService;

import java.io.IOException;
import java.util.Map;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside Do Post Method");
        // Get the JSON data from the request
        String body = new String(request.getInputStream().readAllBytes());
        System.out.println("Body->"+body);

        // Convert the JSON data to a Map using Gson
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        UserService service = new UserService();
        service.saveUser(user);

        // Use the data in the Map
        String username = user.getName();
        System.out.println("usernname "+ username+"credit "+user.getCreditLimit());

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside Do Get Method");
    }


}