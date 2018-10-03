package com.nitto.tushar.nrrii.DAO;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.nitto.tushar.nrrii.Entity.ProductItem;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM ProductItem where uid=:uid")
    ProductItem getProductByUID(int uid);

    @Query("SELECT * FROM ProductItem")
    List<ProductItem> getAll();

    @Query("SELECT * FROM ProductItem where product_name LIKE  :ProductName AND product_description LIKE :ProductDescription AND product_price LIKE :ProductPrice AND product_rating LIKE :ProductRating AND product_image LIKE :ProductImage")
    ProductItem findByName(String ProductName, String ProductDescription, double ProductPrice, int ProductRating, String ProductImage);

    @Query("SELECT COUNT(*) from ProductItem")
    int countProducts();

    @Insert
    void insert(ProductItem productItem);

    @Insert
    void insertAll(ProductItem... productItems);

    @Delete
    void delete(ProductItem productItem);
}
