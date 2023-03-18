package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.Product;
import org.service.CategoryService;
import org.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class CategoryServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String[] filters = req.getParameter("filters").split(",");
        System.out.println("Hello from category servlet");
        CategoryService service = new CategoryService();
        List<Product> products = service.getProductsOnCategory(filters);
        for (Product product:products ) {
            System.out.println("product name is ->"+product.getName());
        }
        req.setAttribute("categoredproducts",products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }
}
