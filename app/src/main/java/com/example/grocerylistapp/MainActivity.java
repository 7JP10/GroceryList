package com.example.grocerylistapp;

import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.grocerylistapp.model.CategoryModel;
//import com.example.grocerylistapp.model.ItemModel;
import com.example.grocerylistapp.repo.ListRepository;
public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryLongClickListener {

    private List<Category> categories;
    private CategoryAdapter adapter;

    private ListRepository listRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRepo = ListRepository.getInstance(this);
        List<CategoryModel> getCategories = listRepo.getCategories();

        /**/
        //categories.add(new Category(categoryName, selectedColor));
        //adapter.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        int spanCount = 2; // Set the number of items per row
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        categories = new ArrayList<>();
        adapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(adapter);

        StartingData(getCategories); //SETS UP STARTING DATA BASED ON WHAT WAS SAVED ON DB

        // Add a button to add a new category (for simplicity, without a separate activity)
        Button addCategoryButton = findViewById(R.id.btnAddCategory);
        addCategoryButton.setOnClickListener(v -> {
            showAddCategoryDialog();
        });
    }

    @Override
    public void onCategoryLongClick(int position) {
        showEditDeleteDialog(position);
    }

    public void StartingData(List<CategoryModel> getCategories){

        for(int i = 0; i < getCategories.size(); i++){
            String name = getCategories.get(i).getCategory();
            String color = getCategories.get(i).getColor();
            categories.add(new Category(name, color));
            adapter.notifyDataSetChanged();
        }
    }

    private void showAddCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Category");

        // Inflate the custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_category, null);
        builder.setView(dialogView);

        final EditText categoryNameInput = dialogView.findViewById(R.id.etCategoryName);

        // Get the spinner and populate it with the colors array
        Spinner spinnerColors = dialogView.findViewById(R.id.spinnerColors);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.color_names, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColors.setAdapter(colorAdapter);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String categoryName = categoryNameInput.getText().toString().trim();
            if (!categoryName.isEmpty()) {
                String selectedColor = spinnerColors.getSelectedItem().toString();
                categories.add(new Category(categoryName, selectedColor));
                adapter.notifyDataSetChanged();

                CategoryModel cM = new CategoryModel(categoryName, selectedColor);
                listRepo.addCategory(cM);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showEditDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit or Delete Category");

        builder.setItems(new String[]{"Edit", "Delete"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    showEditCategoryDialog(position);
                    break;
                case 1:
                    categories.remove(position);
                    adapter.notifyDataSetChanged();

                    List<CategoryModel> getCategories = listRepo.getCategories();

                    for(int i = 0; i < getCategories.size(); i++){
                        if(i == position){
                            listRepo.deleteCategory(getCategories.get(i));
                        }
                    }
                    break;
            }
        });

        builder.show();
    }

    private void showEditCategoryDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Category");

        // Inflate the custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_category, null);
        builder.setView(dialogView);

        final EditText categoryNameInput = dialogView.findViewById(R.id.etCategoryName);

        // Get the spinner and populate it with the colors array
        Spinner spinnerColors = dialogView.findViewById(R.id.spinnerColors);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.color_names, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColors.setAdapter(colorAdapter);

        Category category = categories.get(position);
        categoryNameInput.setText(category.getCategoryName());

        // Set the initial color in the spinner
        int selectedColorIndex = getColorIndex(category.getInitialColor());
        spinnerColors.setSelection(selectedColorIndex);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newCategoryName = categoryNameInput.getText().toString().trim();
            if (!newCategoryName.isEmpty()) {
                String selectedColor = spinnerColors.getSelectedItem().toString();
                category.setCategoryName(newCategoryName);
                category.setInitialColor(selectedColor);
                adapter.notifyDataSetChanged();

                List<CategoryModel> getCategories = listRepo.getCategories();

                for(int i = 0; i < getCategories.size(); i++){
                    if(i == position){
                        getCategories.get(i).setCategory(newCategoryName);
                        getCategories.get(i).setColor(selectedColor);
                        listRepo.updateCategory(getCategories.get(i));
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private int getColorIndex(String color) {
        String[] colors = getResources().getStringArray(R.array.color_names);
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(color)) {
                return i;
            }
        }
        return 0; // Default to the first color if no match is found
    }


}
//here