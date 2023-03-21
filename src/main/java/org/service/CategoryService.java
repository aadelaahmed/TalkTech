package org.service;

import org.persistence.dao.CategoryDao;
import org.persistence.entities.Product;

import java.util.List;

public class CategoryService {
    CategoryDao categoryDao;
    public CategoryService(){
        this.categoryDao = new CategoryDao();
    }
    public List<Product> getProductsOnCategory(List<Integer> categoryIds,List<String> brands){
        //select * from product where productId IN (ids) AND brand IN (brands);
        return categoryDao.getProductsOnCategory(categoryIds,brands);
    }
}
