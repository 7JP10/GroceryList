package com.example.grocerylistapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CategoryModel.class, parentColumns = "id",
        childColumns = "category_id", onDelete = CASCADE))
public class ItemModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "item_name")
    private String mItemName;

    @ColumnInfo(name = "item_description")
    private String mItemDescription;

    @ColumnInfo(name = "category_id")
    private long mCategoryId;

    //************DEFINITION***********

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String name) {
        mItemName = name;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String description) {
        mItemDescription = description;
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }
}