package com.example.thoithanh.shoppingappp;

/**
 * Created by thoithanh on 5/9/18.
 */

public class Category {
    public int Id;
    public String Name;
    public String Components;
    public String ResultNumber;

    public Category(int id, String name, String components, String resultNumber) {
        Id = id;
        Name = name;
        Components = components;
        ResultNumber = resultNumber;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getComponents() {
        return Components;
    }

    public String getResultNumber() {
        return ResultNumber;
    }
}
