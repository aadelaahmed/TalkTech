package org.service;

import org.persistence.dao.ProductDao;
import org.persistence.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeService {
    ProductDao productDao;
    List<Product> products = new ArrayList<>();
    int limitRow = 3;
    public HomeService(){
        this.productDao = new ProductDao();
    }

    public List<Product> getLimitedProducts(int limit){
        List<Product> products = productDao.getLimitedProducts(limit);
        /*System.out.println("from product service -->"+products);
        for (Product product:products) {
            System.out.println(product.getPrice() + "  "+ product.getName());
        }*/
        return products;
    }
    public List<Product> getFirstRowProducts(int limit){
        products = productDao.getLimitedProducts(limit);
        return products.subList(1,limitRow);
    }
    public List<Product> getSecondRowProducts(){
        return products.subList(limitRow+1,limitRow+3);
    }

}
