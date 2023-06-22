package com.example.smartlab;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

public class BasketModel {
    public String id;
    public String title;

    public String price;

    public BasketModel(String id, String title,  String price) {
        this.id = id;
        this.price = price;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null||getClass()!=o.getClass())return false;
        BasketModel basketModel = (BasketModel) o;
        return Objects.equals(title,basketModel.title);
    }
    @Override
    public int hashCode(){
        return Objects.hash(title);
    }

    public String getPrice(){
        return price;
    }

}
