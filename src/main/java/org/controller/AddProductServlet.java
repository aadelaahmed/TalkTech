package org.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.persistence.entities.Product;
import org.service.ProductService;

import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside Do Post Method In Add Product");
        // Get the JSON data from the request
        String body = new String(request.getInputStream().readAllBytes());
        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(body);

        // Get the email value from the JSONObject
        String Name = jsonObject.getString("name");
        System.out.println("Body->" + body);

        // Convert the JSON data to a Map using Gson
        Gson gson = new Gson();
        Product product = gson.fromJson(body, Product.class);
        System.out.println("product" + product.toString());
        ProductService service = new ProductService();
        service.save(product);

        // Use the data in the Map
        String name = product.getName();
        System.out.println("Name:" + name + " Color:" + product.getColor());
        request.getRequestDispatcher("adminProducts.jsp").forward(request, response);
    }
}
