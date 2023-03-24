package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.service.ProductService;

import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside doGet DeleteProductServlet");
        Integer id = Integer.valueOf(req.getParameter("productId"));
        System.out.println("productId= " + id);
        ProductService productService = new ProductService();
        productService.deleteProductById(id);
        req.getRequestDispatcher("AdminProductsServlet").forward(req, resp);

    }
}
