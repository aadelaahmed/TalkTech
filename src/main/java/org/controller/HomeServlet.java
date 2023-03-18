package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.Product;
import org.service.HomeService;
import org.service.ProductService;
import java.util.*;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HomeService service = new HomeService();
        System.out.println("Hello from home servlet");
        List<Product> products = service.getLimitedProducts(6);
        /*System.out.println(products);
        for (Product product:products) {
            System.out.println(product.getPrice() + "  "+ product.getName());
        }*/
        req.setAttribute("limitedproducts",products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        /*ProductService service = new ProductService();
        System.out.println("Hello from home servlet");
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
