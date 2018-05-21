package com.example.thoithanh.shoppingappp;

/**
 * Created by thoithanh on 5/15/18.
 */

public class DccItem {
    public int Id;
    public String ImgURL;
    public String Name;
    public String Price;
    public String OriginalPrice;
    public Integer Quantity;

    public DccItem(int id, String imgURL, String name, String price, String originalPrice, Integer quantity) {
        Id = id;
        ImgURL = imgURL;
        Name = name;
        Price = price;
        OriginalPrice = originalPrice;
        Quantity = quantity;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setOriginalPrice(String originalPrice) {
        OriginalPrice = originalPrice;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public String getImgURL() {
        return ImgURL;
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

    public Integer getQuantity() {
        return Quantity;
    }
}
