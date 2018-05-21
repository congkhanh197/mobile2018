package com.example.thoithanh.shoppingappp;

/**
 * Created by thoithanh on 5/11/18.
 */

public class DlgItem {
    public int Id;
    public String Name;
    public String Price;
    public String OriginalPrice;
    public String ImgURL;

    public DlgItem(int id,String name, String price, String originalPrice, String imgURL) {
        Id = id;
        Name = name;
        Price = price;
        OriginalPrice = originalPrice;
        ImgURL = imgURL;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getOriginalPrice() {
        return OriginalPrice;
    }

    public String getImgURL() {
        return ImgURL;
    }
}
