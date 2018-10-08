package com.nitto.tushar.nrrii.Entity;

public class CartItem {

    private String productTitle;
    private int productQuantity;
    private int productPrice;
    private String productSize;
    private String productColor;

    public CartItem() {
    }

    public CartItem(int productQuantity, int productPrice, String productSize, String productColor) {
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;
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
