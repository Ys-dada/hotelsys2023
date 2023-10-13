package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Product;
import com.abc.hotelsys.service.ProductQueryHelper;

import java.util.List;

public interface ProductDao {
    List<Product> loadProducts();
    List<Product> loadProductsByHelper(ProductQueryHelper helper);
    Product getProductById(Integer productId);
    void delProductById(Integer productId);
    void updateProduct(Product product);
    void addProduct(Product product);

}
