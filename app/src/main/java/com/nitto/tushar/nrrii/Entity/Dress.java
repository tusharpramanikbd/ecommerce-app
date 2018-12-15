package com.nitto.tushar.nrrii.Entity;

import java.util.ArrayList;

public class Dress {

    private String dressId;
    private String dressTitle;
    private int dressCoverPhoto;
    private int[] dressImages;
    private String actualPrice;
    private String promotionalPrice;
    private String dressDetails;
    private ArrayList<DressReview> dressReviews;
    private ArrayList<ImageItem> images;

    public Dress() {
    }

    public Dress(String dressId, String actualPrice, String promotionalPrice) {
        this.dressId = dressId;
        this.actualPrice = actualPrice;
        this.promotionalPrice = promotionalPrice;
    }

    public Dress(String dressId, int dressCoverPhoto, int[] dressImages, String actualPrice) {
        this.dressId = dressId;
        this.dressCoverPhoto = dressCoverPhoto;
        this.dressImages = dressImages;
        this.actualPrice = actualPrice;
    }

    public ArrayList<ImageItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageItem> images) {
        this.images = images;
    }

    public int getDressCoverPhoto() {
        return dressCoverPhoto;
    }

    public void setDressCoverPhoto(int dressCoverPhoto) {
        this.dressCoverPhoto = dressCoverPhoto;
    }

    public String getDressId() {
        return dressId;
    }

    public void setDressId(String dressId) {
        this.dressId = dressId;
    }

    public String getDressTitle() {
        return dressTitle;
    }

    public void setDressTitle(String dressTitle) {
        this.dressTitle = dressTitle;
    }

    public int[] getDressImages() {
        return dressImages;
    }

    public void setDressImages(int[] dressImages) {
        this.dressImages = dressImages;
    }

    public String getDressDetails() {
        return dressDetails;
    }

    public void setDressDetails(String dressDetails) {
        this.dressDetails = dressDetails;
    }

    public ArrayList<DressReview> getDressReviews() {
        return dressReviews;
    }

    public void setDressReviews(ArrayList<DressReview> dressReviews) {
        this.dressReviews = dressReviews;
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
