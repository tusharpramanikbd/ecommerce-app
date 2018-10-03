package com.nitto.tushar.nrrii.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ProductItem")
public class ProductItem {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @SerializedName("product_name")
    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "product_description")
    private String productDescription;

    @SerializedName("price")
    @ColumnInfo(name = "product_price")
    private double price;

    @ColumnInfo(name = "product_rating")
    private int rating;

    @SerializedName("product_image")
    @ColumnInfo(name = "product_image")
    private String image;

    @ColumnInfo(name = "product_quantity")
    private int quantity;

    @SerializedName("category_id")
    @ColumnInfo(name = "category_number")
    private int categoryNumber;



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProductImage() {
        return image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }
}

