package com.abc.hotelsys.service;

import com.abc.hotelsys.dao.ProductDao;
import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> loadProducts();
    List<Product> loadProductsByHelper(ProductQueryHelper helper);
    Product getProductById(Integer productId);
    void delProductById(Integer productId);
    void updateProduct(Product product);
    void addProduct(Product product);
}
