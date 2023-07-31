package com.example.grocerylistapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private OnCategoryLongClickListener longClickListener;

    public CategoryAdapter(List<Category> categories, OnCategoryLongClickListener longClickListener) {
        this.categories = categories;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryName.setText(category.getCategoryName());

        // Apply the selected color to the CardView background
        int backgroundColor = getColorFromSelectedColor(category.getInitialColor());
        holder.cardView.setCardBackgroundColor(backgroundColor);

        // Set a click listener for the category item
        holder.itemView.setOnClickListener(view -> {
            // Start the ItemListActivity with the selected category's item list
            Intent intent = new Intent(view.getContext(), ItemListActivity.class);
            intent.putExtra("categoryName", category.getCategoryName());
            intent.putParcelableArrayListExtra("itemList", (ArrayList<Item>) category.getItemList());
            view.getContext().startActivity(intent);
        });
    }


    private int getColorFromSelectedColor(String selectedColor) {
        switch (selectedColor) {
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "Blue":
                return Color.BLUE;
            case "Yellow":
                return Color.YELLOW;
            case "Purple":
                return Color.parseColor("#800080");
            case "Orange":
                return Color.parseColor("#FFA500");
            default:
                return Color.WHITE; // Default to white if no color is matched
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView categoryName;
        CardView cardView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tvCategoryName);
            cardView = itemView.findViewById(R.id.cardView);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (longClickListener != null) {
                longClickListener.onCategoryLongClick(getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    // Interface to handle long click events on categories
    public interface OnCategoryLongClickListener {
        void onCategoryLongClick(int position);
    }
}
