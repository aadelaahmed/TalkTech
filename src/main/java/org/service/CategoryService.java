package org.service;

import org.persistence.dao.CategoryDao;
import org.persistence.entities.Product;
import org.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    CategoryDao categoryDao;
    List<String> categories = new ArrayList<>();
    List<String> brands = new ArrayList<>();
    public CategoryService(){
        this.categoryDao = new CategoryDao();
    }
    public List<Product> getProductsOnCategory(String[] filters){
        //select * from product where productId IN (ids) AND brand IN (brands);
        for (String filter : filters) {
            if(Constants.categories.contains(filter))
                categories.add(filter);
            else if (Constants.brands.contains(filter))
                brands.add(filter);
        }
        return categoryDao.getProductsOnCategory(categories,brands);
    }
}
