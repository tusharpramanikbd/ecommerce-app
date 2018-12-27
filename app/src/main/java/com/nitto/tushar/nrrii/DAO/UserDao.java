package com.nitto.tushar.nrrii.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nitto.tushar.nrrii.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User where userid=:uid")
    User getProductByUID(int uid);

    @Query("SELECT * FROM User")
    List<User> getAll();

//    @Query("SELECT * FROM User where userName LIKE  :userName AND product_description LIKE :ProductDescription AND product_price LIKE :ProductPrice AND product_rating LIKE :ProductRating AND product_image LIKE :ProductImage")
//    User findByName(String userName, String ProductDescription, double ProductPrice, int ProductRating, String ProductImage);

    @Query("SELECT COUNT(*) from User")
    int countProducts();

    @Insert
    long insert(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Update
    int update(User user);
}
