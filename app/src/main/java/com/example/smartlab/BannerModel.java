package com.example.smartlab;

public class BannerModel {

    public String id;
    public String title;
    public String description;

    public String price;

    public BannerModel(String id, String title, String description, String price) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice(){
        return price;
    }

}
