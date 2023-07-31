package com.example.grocerylistapp.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.grocerylistapp.model.CategoryModel;
import com.example.grocerylistapp.model.ItemModel;

@Database(entities = {ItemModel.class, CategoryModel.class}, version = 1)
public abstract class ListDB extends RoomDatabase {

    public abstract ItemDAO itemDao();
    public abstract CategoryDAO categoryDAO();
}