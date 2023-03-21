package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.beans.ProductBean;
import org.persistence.entities.Product;
import org.service.ProductService;
import org.util.Constants;

import java.io.IOException;

public class ProductDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("Hello from product details");
        Integer productId = Integer.valueOf(req.getParameter("productid"));
        System.out.println("product id ->"+productId);
        Product product = new ProductService().getProductById(productId);
        ProductBean productBean = new ProductBean(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory(),
                product.getColor()
        );
        System.out.println("product -> "+product.toString());
        Gson gson = new Gson();
        String productJson = gson.toJson(productBean);
        req.setAttribute(Constants.PRODUCT_ATTRIBUTE,productBean);
        req.setAttribute(Constants.PRODUCT_ATTRIBUTE_JSON,productJson);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("productdetails.jsp");
        requestDispatcher.forward(req,resp);
    }
}
