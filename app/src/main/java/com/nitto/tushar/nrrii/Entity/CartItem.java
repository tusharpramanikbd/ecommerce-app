package com.nitto.tushar.nrrii.Entity;

public class CartItem {

    private String productId;
    private String productTitle;
    private String productPhoto;
    private int productQuantity;
    private double productPrice;
    private String productSize;
    private String productColor;

    public CartItem() {
    }

    public CartItem(String productId,String productPhoto,int productQuantity, double productPrice, String productSize, String productColor) {
        this.productId = productId;
        this.productPhoto = productPhoto;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
    }

    public CartItem(String productId, String productTitle, String productPhoto, int productQuantity, double productPrice, String productSize, String productColor) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productPhoto = productPhoto;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
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
