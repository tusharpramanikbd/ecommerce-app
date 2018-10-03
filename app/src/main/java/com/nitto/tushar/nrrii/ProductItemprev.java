package com.nitto.tushar.nrrii;

import android.graphics.Bitmap;

/**
 * Created by masud on 2/5/2018.
 */

public class ProductItemprev {
    private String name;
    private double price;
    private int rating;
    private Bitmap image;
    private String color;
    private String size;
    private int quantity;

    public ProductItemprev(){

    }

    public ProductItemprev(String name, double price, int rating, Bitmap image, String color, String size, int quantity) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}