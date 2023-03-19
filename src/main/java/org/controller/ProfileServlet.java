package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.persistence.entities.User;
import org.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("inside profile doPost");
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        System.out.println("email from session is: " + email);
        User user = new UserService().findByEmail(email.trim());
        session.setAttribute("user", user);
        // Convert the user object to a JSON string
        Gson gson = new Gson();
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
