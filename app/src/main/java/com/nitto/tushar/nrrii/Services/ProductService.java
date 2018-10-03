package com.nitto.tushar.nrrii.Services;



import com.nitto.tushar.nrrii.Entity.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductItem productItem;

    private static ProductService productService = new ProductService();

    public static ProductService getInstance() {
        if(productService==null){
            Class clazz = ProductService.class;
            synchronized (clazz){
                productService = new ProductService();
            }
        }

        return productService;
    }

    // Constructor
    private ProductService(){
//        // all class related initialization goes here
//        this.orderItemsList = new ArrayList<>();
    }

    public void InitializeProductService() {
        this.productItem = null;
    }

    public void insertProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public ProductItem getInsertedProductItem(){
        return this.productItem;
    }

}
