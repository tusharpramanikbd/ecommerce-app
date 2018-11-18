package com.nitto.tushar.nrrii.Entity;

public class CartItem {

    private String productId;
    private String productTitle;
    private int productPhoto;
    private int productQuantity;
    private int productPrice;
    private String productSize;
    private String productColor;

    public CartItem() {
    }

    public CartItem(String productId,int productPhoto,int productQuantity, int productPrice, String productSize, String productColor) {
        this.productId = productId;
        this.productPhoto = productPhoto;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
    }

    public CartItem(String productId, String productTitle, int productPhoto, int productQuantity, int productPrice, String productSize, String productColor) {
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

    public int getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(int productPhoto) {
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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
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
