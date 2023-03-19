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
import java.io.PrintWriter;

public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String emailSession = (String) session.getAttribute("email");

        System.out.println("Inside Edit Profile Do Post Method");
        System.out.println("email form session: "+emailSession);
        // Get the JSON data from the request
        String body = new String(request.getInputStream().readAllBytes());
        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(body);

        // Get the email value from the JSONObject
        String email = jsonObject.getString("email");
        System.out.println("Body->" + body);
        System.out.println("email from json" + email);
        // setting attributes
        session.setAttribute("email", email);

        // Convert the JSON data to a Map using Gson
        Gson gson = new Gson();
        User user = gson.fromJson(body, User.class);
        UserService service = new UserService();
        service.updateUser(user, emailSession.trim());
        session.setAttribute("email", email);
        System.out.println("user updated");
        // Use the data in the Map
        String username = user.getName();
        System.out.println("usernname " + username + "credit " + user.getCreditLimit());
        String json = gson.toJson(user);

        // Set the response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write the JSON string to the response
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();


    }
}
