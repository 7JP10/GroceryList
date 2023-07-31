package com.example.grocerylistapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String itemName;
    private String itemDescription;

    // Constructors, getters, setters, and other methods...
    String getItemName() {return itemName;}
    void setItemName(String itemName){this.itemName = itemName;}
    public Item(String name){
        itemName = name;
        itemDescription = name;
    }


    // Implement Parcelable methods
    protected Item(Parcel in) {
        itemName = in.readString();
        itemDescription = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemDescription);
    }
}

