package com.nitto.tushar.nrrii.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "CartItem")
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    private long cartId;

    private String productId;

    private String productTitle;

    private String productPhoto;

    private int productQuantity;

    private double productPrice;

    private String productSize;

    private String productColor;

    public CartItem() {
    }

    @Ignore
    public CartItem(String productId,String productPhoto,int productQuantity, double productPrice, String productSize, String productColor) {
        this.productId = productId;
        this.productPhoto = productPhoto;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
    }

    @Ignore
    public CartItem(String productId, String productTitle, String productPhoto, int productQuantity, double productPrice, String productSize, String productColor) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productPhoto = productPhoto;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
}
