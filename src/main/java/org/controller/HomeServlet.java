package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.persistence.entities.Product;
import org.service.HomeService;
import org.service.ProductService;
import java.util.*;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HttpSession session = req.getSession(false);
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        if(session!=null) {
            String userType = (String) session.getAttribute("userType");
            if (userType!=null && userType.trim().equals("admin")) {
                HttpServletResponse httpResponse = (HttpServletResponse) resp;
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin.jsp");
            }
            else{
                HomeService service = new HomeService();
                System.out.println("Hello from index.jsp servlet");
                List<Product> products = service.getLimitedProducts(6);
                req.setAttribute("limitedproducts", products);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
        else {
            HomeService service = new HomeService();
            System.out.println("Hello from index.jsp servlet");
            List<Product> products = service.getLimitedProducts(6);
            req.setAttribute("limitedproducts", products);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        /*ProductService service = new ProductService();
        System.out.println("Hello from index.jsp servlet");
        List<Product> products = service.getLimitedProducts(6);
        System.out.println(products);
        for (Product product:products) {
            System.out.println(product.getPrice() + "  "+ product.getName());
        }
        req.setAttribute("productList",products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);*/
    }
}
