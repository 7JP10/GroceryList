package com.example.grocerylistapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;

import java.util.List;
import java.util.ArrayList;

public class Category implements Parcelable {
    private String categoryName;
    private String initialColor;
    private List<Item> itemList;



    // Add more properties if needed

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public Category(String categoryName, String initialColor) {
        this.categoryName = categoryName;
        this.initialColor = initialColor;
        this.itemList = new ArrayList<>();
    }

    // Getter for initialColor
    public String getInitialColor() {
        return initialColor;
    }

    // Setter for initialColor (if needed)
    public void setInitialColor(String initialColor) {
        this.initialColor = initialColor;
    }
    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }
    // Parcelable implementation
    protected Category(Parcel in) {
        categoryName = in.readString();
        initialColor = in.readString();
        itemList = in.createTypedArrayList(Item.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
        dest.writeString(initialColor);
        dest.writeTypedList(itemList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
