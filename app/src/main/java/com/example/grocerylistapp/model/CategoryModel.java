package com.example.grocerylistapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "category_name")
    private String mCategory;

    @ColumnInfo(name = "color")
    private String mColor;

    public CategoryModel(@NonNull String mCategory, String mColor) {
        this.mCategory = mCategory;
        this.mColor = mColor;
    }
    //**********DEFINITION*************

    public long getId() {
        return mId;
    }
    public void setId(long id) {
        mId = id;
    }

    //***********

    public String getCategory() {
        return mCategory;
    }
    public void setCategory(String category) {
        mCategory = category;
    }

    //***********

    public String getColor() {
        return mColor;
    }
    public void setColor(String color) {
        mColor = color;
    }

}