package com.example.grocerylistapp.repo;

import android.content.Context;
import androidx.room.Room;
import com.example.grocerylistapp.model.CategoryModel;
import com.example.grocerylistapp.model.ItemModel;
import java.util.List;

public class ListRepository {

    private static ListRepository mListRepo;
    private final CategoryDAO mCategoryDao;
    private final ItemDAO mItemDao;

    public static ListRepository getInstance(Context context) {
        if (mListRepo == null) {
            mListRepo = new ListRepository(context);
        }
        return mListRepo;
    }

    private ListRepository(Context context) {
        ListDB database = Room.databaseBuilder(context, ListDB.class, "GroceryList.db")
                .allowMainThreadQueries()
                .build();

        mCategoryDao = database.categoryDAO();
        mItemDao = database.itemDao();
    }

    public void addCategory(CategoryModel categoryModel) {
        long categoryId = mCategoryDao.addCategory(categoryModel);
        categoryModel.setId(categoryId);
    }

    public CategoryModel getCategory(long categoryId) {
        return mCategoryDao.getCategory(categoryId);
    }

    public List<CategoryModel> getCategories() {
        return mCategoryDao.getCategories();
    }

    public void updateCategory(CategoryModel categoryModelModel) {
        mCategoryDao.updateCategory(categoryModelModel);
    }

    public void deleteCategory(CategoryModel categoryModel) {
        mCategoryDao.deleteCategory(categoryModel);
    }

    //**************

    public ItemModel getItem(long itemId) {
        return mItemDao.getItem(itemId);
    }

    public List<ItemModel> getItems(long categoryId) {
        return mItemDao.getItems(categoryId);
    }

    public void addItem(ItemModel itemModel) {
        long questionId = mItemDao.addItem(itemModel);
        itemModel.setId(questionId);
    }

    public void updateItem(ItemModel itemModel) {
        mItemDao.updateItem(itemModel);
    }

    public void deleteItem(ItemModel itemModel) {
        mItemDao.deleteItem(itemModel);
    }

}