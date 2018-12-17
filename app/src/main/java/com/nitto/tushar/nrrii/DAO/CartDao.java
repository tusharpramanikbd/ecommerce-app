package com.nitto.tushar.nrrii.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nitto.tushar.nrrii.Entity.CartItem;

import java.util.List;

@Dao
public interface CartDao
{
    @Query("SELECT * FROM CartItem where cartId=:cid")
    CartItem getCartItemByOID(long cid);

    @Query("SELECT * FROM CartItem")
    List<CartItem> getAllCartItem();

//    @Query("SELECT * FROM OrderItem where product_name LIKE  :ProductName AND product_description LIKE :ProductDescription AND product_price LIKE :ProductPrice AND product_rating LIKE :ProductRating AND product_image LIKE :ProductImage")
//    OrderItem findByName(String ProductName, String ProductDescription, double ProductPrice, int ProductRating, String ProductImage);

//    @Query("SELECT COUNT(*) from ProductItem")
//    int countProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(CartItem cartItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<CartItem> cartItems);

    @Query("DELETE FROM CartItem WHERE cartId=:cid")
    int delete(long cid);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateCartItem(List<CartItem> cartItemList);
}
