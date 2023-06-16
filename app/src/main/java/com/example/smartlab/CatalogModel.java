package com.example.smartlab;

public class CatalogModel {
    public String id;
    public String title;
    public String description;
    public String time_result;

    public String price;

    public CatalogModel(String id, String title, String description, String price, String time_result) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.time_result = time_result;
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
    public String getTimeResult(){return time_result;}
}
