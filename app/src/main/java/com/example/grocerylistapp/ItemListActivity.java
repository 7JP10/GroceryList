package com.example.grocerylistapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.grocerylistapp.model.CategoryModel;
import com.example.grocerylistapp.model.ItemModel;
import com.example.grocerylistapp.repo.ListRepository;
public class ItemListActivity extends AppCompatActivity implements ListAdapter.OnListLongClickListener{

    private List<Item> items;
    private ListAdapter adapter;
    private EditText itemEditText;
    private ListRepository listRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        listRepo = ListRepository.getInstance(this);

        List<ItemModel> getItems = listRepo.getItems(6); //change to correct category id **********

        itemEditText = findViewById(R.id.Grocery_item);

        RecyclerView recyclerView = findViewById(R.id.itemRecyclerView);

        int spanCount = 1; // Set the number of items per row
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        items = new ArrayList<>();
        adapter = new ListAdapter(items, this);
        recyclerView.setAdapter(adapter);

        StartingData(getItems);
    }

    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    public void StartingData(List<ItemModel> getItems){ //change to correct category id **********

        for(int i = 0; i < getItems.size(); i++){
            String name = getItems.get(i).getItemName();
            String description = getItems.get(i).getItemDescription(); //NOT NECESSERY
            items.add(new Item(name));
            adapter.notifyDataSetChanged();
        }
    }
    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

    public void addClick(View view){
        String itemName = itemEditText.getText().toString().trim();
        if (!itemName.isEmpty()){
            items.add(new Item(itemName));
            itemEditText.getText().clear();
            adapter.notifyDataSetChanged();

            ItemModel iM = new ItemModel();
            iM.setItemName(itemName);
            iM.setCategoryId(6);  //CHANGE TO CORRECT INDEX
            listRepo.addItem(iM);
        }
    }

    @Override
    public void onListLongClick(int position) {
        showDeleteDialog(position);
    }

    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Item?");

        builder.setItems(new String[]{"Yes", "No"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    items.remove(position);
                    adapter.notifyDataSetChanged();

                    List<ItemModel> getItems = listRepo.getItems(6); //change to correct category id *******

                    for(int i = 0; i < getItems.size(); i++){
                        if(i == position){
                            listRepo.deleteItem(getItems.get(i));
                        }
                    }
                    break;
                case 1:
                    break;
            }
        });

        builder.show();
    }

}