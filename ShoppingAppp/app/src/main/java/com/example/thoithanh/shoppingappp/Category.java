package com.example.thoithanh.shoppingappp;

/**
 * Created by thoithanh on 5/9/18.
 */

public class Category {
    public String Name;
    public String Components;
    public String ResultNumber;

    public Category(String name, String components, String resultNumber) {
        Name = name;
        Components = components;
        ResultNumber = resultNumber;
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
