package org.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.persistence.entities.Product;
import org.service.ProductService;

import java.io.*;
import java.math.BigDecimal;

@MultipartConfig
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside Do Post Method In Add Product");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Integer.parseInt(request.getParameter("price")));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");

        //Getting the product image part and file content for Uploading
        Part filePart = request.getPart("file");

        InputStream fileContent = filePart.getInputStream();
        //String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\images";
        String savePath = "C:\\apache-tomcat-10.1.5\\webapps\\images";
        String fileName = getFileName(filePart);
        OutputStream out = new FileOutputStream(new File(savePath + File.separator + fileName));
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        // A relative URL of the to be accessible in the application (fixing cannot access local resource)
        String filePath = "../../images" + "/" + fileName;
        ProductService productService = new ProductService();
        Product product = new Product(name, description, brand, price, quantity, category, color, filePath);
        productService.save(product);
        System.out.println("File " + fileName + " has been uploaded to " + savePath);

        System.out.println(filePath);
        response.sendRedirect("AdminProductsServlet");


    }

    private String getFileName(Part filePart) {
        String contentDisp = filePart.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
