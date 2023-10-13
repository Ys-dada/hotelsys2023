package com.abc.hotelsys.controller;

import com.abc.hotelsys.dao.ProductDao;
import com.abc.hotelsys.dao.ProductDaoMyBatisImpl;
import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Product;
import com.abc.hotelsys.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import sun.text.normalizer.IntTrie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class HotelMgrServlet
 */
@WebServlet("/productMgr")
public class productMgrServlet extends ViewBaseServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(productMgrServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public productMgrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String task = request.getParameter("task");
        if("toInput".equals(task)){
            this.processTemplate("/product/input_product",request,response);
        }else if("loadProducts".equals(task))
        {
            ProductService productService=new ProductServiceImpl();

            ProductQueryHelper helper=new ProductQueryHelper();

            if(StringUtils.isNotEmpty(request.getParameter("qryProductType")))
                helper.setQryProductType(request.getParameter("qryProductType"));

            List<Product> productList = productService.loadProductsByHelper(helper);
            request.setAttribute("productList",productList);
            request.setAttribute("helper",helper);
            this.processTemplate("/product/list_products",request,response);
        }
        else if("create".equals(task))
        {
            Product product=new Product();
            product.setProductName(request.getParameter("productName"));
            product.setProductPrice(Double.valueOf(request.getParameter("productPrice")));
            product.setProductCount(Integer.valueOf(request.getParameter("productCount")));
            product.setProductType(request.getParameter("productType"));
//            logger.debug("所选择商品的类型是"+product.getProductType());
            ProductService productService=new ProductServiceImpl();
            productService.addProduct(product);
            response.sendRedirect("productMgr?task=loadProducts");
        }
        else if("delete".equals(task))
        {
            Integer prodcutId=Integer.parseInt(request.getParameter("productId"));
            ProductService productService=new ProductServiceImpl();
            productService.delProductById(prodcutId);
            response.sendRedirect("productMgr?task=loadProducts");
        }else if("preUpdate".equals(task))
        {
            Integer prodcutId=Integer.parseInt(request.getParameter("productId"));
            ProductService productService=new ProductServiceImpl();
            Product product=productService.getProductById(prodcutId);
            request.setAttribute("product",product);
            this.processTemplate("/product/update_product",request,response);
        }else if("update".equals(task))
        {
            Product product=new Product();
            product.setProductId(Integer.valueOf(request.getParameter("productId")));
            product.setProductName(request.getParameter("productName"));
            product.setProductPrice(Double.valueOf(request.getParameter("productPrice")));
            product.setProductCount(Integer.valueOf(request.getParameter("productCount")));
            product.setProductType(request.getParameter("productType"));
            ProductService productService=new ProductServiceImpl();
            productService.updateProduct(product);

            response.sendRedirect("productMgr?task=loadProducts");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
