package com.example.smartlab;

public class CategoryModel {
    public String id;
    public String name;
    public CategoryModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }
}
