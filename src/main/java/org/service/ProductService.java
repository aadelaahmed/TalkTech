package org.service;

import org.persistence.dao.ProductDao;
import org.persistence.entities.Product;

public class ProductService {
    ProductDao productDao;
    public ProductService(){
        this.productDao = new ProductDao();
    }
    public void save(Product product){
        productDao.save(product);
    }
}
