package com.example.grocerylistapp.repo;

import androidx.room.*;
import com.example.grocerylistapp.model.CategoryModel;
import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM CategoryModel WHERE id = :id")
    CategoryModel getCategory(long id);

    @Query("SELECT * FROM CategoryModel ORDER BY category_name COLLATE NOCASE")
    List<CategoryModel> getCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addCategory(CategoryModel categoryModel);

    @Update
    void updateCategory(CategoryModel categoryModel);

    @Delete
    void deleteCategory(CategoryModel categoryModel);
}