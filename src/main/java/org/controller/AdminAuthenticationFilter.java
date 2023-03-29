package org.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class AdminAuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("Inside admin Auth Filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // Do not create a new session
        System.out.println("session: "+session);
        if (session != null) {
            String isLoggedIn = (String) session.getAttribute("LoggedIn");
            System.out.println("isLoggedIn? " + isLoggedIn);
            String userType = (String) session.getAttribute("userType");
            System.out.println("userType: "+ userType);

            if(isLoggedIn==null){
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            }
            else{
                if(isLoggedIn.equals("true")){
                    if(userType.trim().equals("Customer")){
                        HttpServletResponse httpResponse = (HttpServletResponse) response;
                        httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
                    }
                    else {
                        // User is logged in, continue processing the request
                        chain.doFilter(request, response);
                    }
                }
                else{
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
                }
            }

        }
        else {
            // User is not logged in, redirect to the login page or return HTTP unauthorized
            // response code (401)
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            // Or: httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}

