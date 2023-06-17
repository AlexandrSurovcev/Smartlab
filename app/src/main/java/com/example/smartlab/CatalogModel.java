package com.example.smartlab;

public class CatalogModel {
    public String id;
    public String title;
    public String description;
    public String time_result;

    public String price;
    public String preparation;
    public String bio;

    public CatalogModel(String id, String title, String description, String price, String time_result,String preparation,String bio) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.time_result = time_result;
        this.preparation=preparation;
        this.bio=bio;
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
    public String getPreparation(){return preparation;}
    public String getBio(){return bio;}
}
