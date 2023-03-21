package org.example;

import org.controller.SignInServlet;
import org.persistence.entities.Product;
import org.persistence.entities.User;
import org.service.ProductService;
import org.service.UserService;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        /*UserService userService = new UserService();
        userService.saveUser(new User("aa@gmail.com"));
        User temp = userService.getUserById(4l);
        System.out.println(temp.getEmail());*/
        Product product = new Product(
                "Amazfit",
                "Amazfit 2 with Bluetooth feature",
                "Amazfit",
                "Black",
                new BigDecimal(2500),
                7,
                3
        );
        ProductService productService = new ProductService();
        productService.save(product);
    }
}
