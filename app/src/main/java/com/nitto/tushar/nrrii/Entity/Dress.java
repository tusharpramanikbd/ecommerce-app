package com.nitto.tushar.nrrii.Entity;

public class Dress {

    private String actualPrice;
    private String promotionalPrice;

    public Dress() {
    }

    public Dress(String actualPrice, String promotionalPrice) {
        this.actualPrice = actualPrice;
        this.promotionalPrice = promotionalPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(String promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }
}
