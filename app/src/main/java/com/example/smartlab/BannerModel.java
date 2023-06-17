package com.example.smartlab;

public class BannerModel {

    public String id;
    public String name;
    public String description;

    public String price;

    public BannerModel(String id, String name, String description, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice(){
        return price;
    }

}
