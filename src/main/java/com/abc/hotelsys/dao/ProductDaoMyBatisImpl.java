package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Product;
import com.abc.hotelsys.domain.User;
import com.abc.hotelsys.exception.DataAccessException;
import com.abc.hotelsys.service.ProductQueryHelper;
import com.abc.hotelsys.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProductDaoMyBatisImpl implements ProductDao {

    @Override
    public List<Product> loadProducts() {
        SqlSession session = MyBatisUtils.getSession();
        List<Product> productList= session.selectList("mybatis.mapper.ProductMapper.loadProducts");
        session.commit();
        MyBatisUtils.closeSession(session);
        return productList;
    }

    @Override
    public List<Product> loadProductsByHelper(ProductQueryHelper helper) {
        SqlSession session = MyBatisUtils.getSession();
        List<Product> productList= session.selectList("mybatis.mapper.ProductMapper.loadProductsByHelper",helper);
        session.commit();
        MyBatisUtils.closeSession(session);
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        SqlSession session = MyBatisUtils.getSession();
        Product product= session.selectOne("mybatis.mapper.ProductMapper.getProductById",productId);
        session.commit();
        MyBatisUtils.closeSession(session);
        return product;
    }

    @Override
    public void delProductById(Integer productId) {
        SqlSession session = MyBatisUtils.getSession();
        session.delete("mybatis.mapper.ProductMapper.delProductById",productId);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public void updateProduct(Product product) {
        SqlSession session = MyBatisUtils.getSession();
        session.update("mybatis.mapper.ProductMapper.updateProduct",product);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public void addProduct(Product product) {
        SqlSession session = MyBatisUtils.getSession();
        session.insert("mybatis.mapper.ProductMapper.addProduct",product);
        session.commit();
        MyBatisUtils.closeSession(session);
    }
}
