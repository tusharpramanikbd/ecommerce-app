package com.nitto.tushar.nrrii.Entity;

public class DressReview {

    private String review;
    private int ratings;

    public DressReview(String review, int ratings) {
        this.review = review;
        this.ratings = ratings;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
