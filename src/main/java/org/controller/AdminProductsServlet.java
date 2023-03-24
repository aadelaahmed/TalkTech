package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.persistence.entities.Product;
import org.service.ProductService;

import java.io.IOException;
import java.util.List;

public class AdminProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductService productService = new ProductService();
        List<Product> productList = productService.getAllProducts();
        // Store the list of data in a request attribute
        request.setAttribute("dataList", productList);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminProducts.jsp");
        dispatcher.forward(request, response);
    }
}
