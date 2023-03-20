package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
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
        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(body);

        // Get the email value from the JSONObject
        String email = jsonObject.getString("email");
        System.out.println("Body->"+body);
        System.out.println("email from local storage" + email);
        // create session for user and setting attributes
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("LoggedIn", "true");

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
