package com.nitto.tushar.nrrii.Entity;

import com.google.gson.annotations.SerializedName;

public class ImageItem {

    @SerializedName("id")
    private int id;

    @SerializedName("src")
    private String src;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
