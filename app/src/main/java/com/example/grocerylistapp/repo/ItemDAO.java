package com.example.grocerylistapp.repo;

import androidx.room.*;
import com.example.grocerylistapp.model.ItemModel;
import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM ItemModel WHERE id = :id")
    ItemModel getItem(long id);

    @Query("SELECT * FROM ItemModel WHERE category_id = :categoryId ORDER BY id")
    List<ItemModel> getItems(long categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addItem(ItemModel itemModel);

    @Update
    void updateItem(ItemModel itemModel);

    @Delete
    void deleteItem(ItemModel itemModel);
}