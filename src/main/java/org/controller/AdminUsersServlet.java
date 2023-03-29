package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.User;
import org.service.UserService;

import java.io.IOException;
import java.util.List;

public class AdminUsersServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println("In User Servlet");

        UserService  userService = new UserService();
        List<User> userList = userService.getAllUser();
        // Store the list of data in a request attribute
        request.setAttribute("dataList", userList);
        for (User us:userList) {
           System.out.println(us.toString());
        }
//        System.out.println(userList.);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminUsers.jsp");
        dispatcher.forward(request, response);
    }

}
