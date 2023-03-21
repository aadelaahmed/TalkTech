package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;
import org.persistence.entities.Product;
import org.service.CategoryService;
import org.service.ProductService;
import org.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class CategoryServlet extends HttpServlet {

    List<Product> products;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String[] filters = req.getParameter("filters").split(",");
        System.out.println("Hello from category servlet");
        CategoryService service = new CategoryService();
        System.out.println("filter values is -> "+filters + " ,its length is -> "+filters.length);
        System.out.println("initial value in filter -> "+filters[0]);
        if (filters[0].equals(""))
        {
            System.out.println("get all products in initial state");
            products = service.getAllProducts();
        }
        else
            products = service.getProductsOnCategory(filters);
        for (Product product:products ) {
            System.out.println("product name is ->"+product.getName());
            /*product.getCartItems().size();
            product.getOrderItems().size();*/
        }
        ObjectMapper mapper = new ObjectMapper();
        String productsStr = mapper.writeValueAsString(products);
        resp.setContentType("application/json");
        resp.getWriter().write(productsStr);
        /*req.setAttribute("categorized_products",products);
        req.getRequestDispatcher("category.jsp").forward(req,resp);*/
        //resp.setAttribute("categoredproducts",products);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }
}
