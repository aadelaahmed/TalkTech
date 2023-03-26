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

public class SignInServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        PrintWriter printWriter = resp.getWriter();

        System.out.println("Inside Do Post Method");
        // Get the JSON data from the request
//        String body = new String(req.getInputStream().readAllBytes());
//        System.out.println("Body->"+body);
        // Parse the JSON string into a JSONObject
//        JSONObject jsonObject = new JSONObject(body);




        // Get the email value from the JSONObject
        String email = req.getParameter("email");
        System.out.println("email from form " + email);
        String password = req.getParameter("password");
        System.out.println("password from form " + password);
        UserService userService = new UserService();

        User user = new User();
        user=userService.findByEmilandPassword(email,password);


        if(user!=null){

            HttpSession session = req.getSession(true);
            session.setAttribute("email", email);
            session.setAttribute("LoggedIn", "true");
            session.setAttribute("userType",user.getUserType());

            //req.getRequestDispatcher("home").forward(req,resp);

            resp.sendRedirect("home");
//                     .forward(req,resp);
        }
        else{
            printWriter.println("Email or password are not valid.");
        }





        // create session for user and setting attributes
//        HttpSession session = req.getSession(true);
//        session.setAttribute("email", email);
//        session.setAttribute("LoggedIn", email);



        //session.setAttribute(Constants.LOGGED_IN, Constants.LOGGED_IN);
//        session.setAttribute("userType",userType);
//
//        if(Constants.LOGGED_IN.equals("LOGGED_IN")){
//              resp.sendRedirect("index.jsp");//url
//        }

        // Convert the JSON data to a Map using Gson
//        Gson gson = new Gson();
//        User user = gson.fromJson(body, User.class);

//        User user = new User();
//        userService.saveUser(user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }
}

