package com.example.thoithanh.shoppingappp;

/**
 * Created by thoithanh on 5/12/18.
 */

public class DsItem {
    public int ItemId;
    public String ImgURL;
    public String Name;
    public String Description;
    public String Price;
    public String OriginalPrice;

    public DsItem(int itemId, String imgURL, String name, String description, String price, String originalPrice) {
        ItemId = itemId;
        ImgURL = imgURL;
        Name = name;
        Description = description;
        Price = price;
        OriginalPrice = originalPrice;
    }

    public int getItemId() {
        return ItemId;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return Price;
    }

    public String getOriginalPrice() {
        return OriginalPrice;
    }
}
