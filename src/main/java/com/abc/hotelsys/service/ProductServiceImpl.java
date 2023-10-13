package com.abc.hotelsys.service;

import com.abc.hotelsys.dao.*;
import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Product;
import com.abc.hotelsys.domain.Room;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private  static  ProductDao productDao=new ProductDaoMyBatisImpl();
    @Override
    public List<Product> loadProducts() {
        return productDao.loadProducts();
    }


    @Override
    public List<Product> loadProductsByHelper(ProductQueryHelper helper) {
        return productDao.loadProductsByHelper(helper);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public void delProductById(Integer productId) {
        productDao.delProductById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }
}
