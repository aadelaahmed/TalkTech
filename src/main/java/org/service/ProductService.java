package org.service;

import org.persistence.dao.ProductDao;
import org.persistence.entities.Product;
import java.util.*;
public class ProductService {
    ProductDao productDao;
    public ProductService(){
        this.productDao = new ProductDao();
    }
    public void save(Product product){
        productDao.save(product);
    }
    public Product getProductById(int id){
        return productDao.getProductById(id);
    }
}
