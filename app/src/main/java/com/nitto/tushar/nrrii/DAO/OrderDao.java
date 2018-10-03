package com.nitto.tushar.nrrii.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.nitto.tushar.nrrii.Entity.OrderItem;

import java.util.List;

@Dao
public interface OrderDao
{
    @Query("SELECT * FROM OrderItem where oid=:oid")
    OrderItem getOrderByOID(int oid);

    @Query("SELECT * FROM OrderItem")
    List<OrderItem> getAllOrder();

//    @Query("SELECT * FROM OrderItem where product_name LIKE  :ProductName AND product_description LIKE :ProductDescription AND product_price LIKE :ProductPrice AND product_rating LIKE :ProductRating AND product_image LIKE :ProductImage")
//    OrderItem findByName(String ProductName, String ProductDescription, double ProductPrice, int ProductRating, String ProductImage);

//    @Query("SELECT COUNT(*) from ProductItem")
//    int countProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(OrderItem orderItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<OrderItem> orderItems);

    @Query("DELETE FROM OrderItem")
    int delete();

    @Update
    int updateOrder(List<OrderItem> orderItemList);
}
