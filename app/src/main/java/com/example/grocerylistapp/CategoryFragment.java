package com.example.grocerylistapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class CategoryFragment extends Fragment {

    private String categoryName;
    private int cardBackgroundColor; // New property to hold the selected color

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String categoryName, int cardBackgroundColor) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("categoryName", categoryName);
        args.putInt("cardBackgroundColor", cardBackgroundColor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString("categoryName");
            cardBackgroundColor = getArguments().getInt("cardBackgroundColor");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_category, container, false);
        TextView categoryNameTextView = view.findViewById(R.id.tvCategoryName);

        if (categoryName != null) {
            categoryNameTextView.setText(categoryName);

            // Set the background color of the CardView based on the selected color
            int backgroundColor = getColorFromSelectedColor(cardBackgroundColor);
            CardView cardView = view.findViewById(R.id.cardView);
            cardView.setCardBackgroundColor(backgroundColor);
        }

        return view;
    }
    ///color array so you can set the color of thew cardview
    private int getColorFromSelectedColor(int selectedColor) {
        switch (selectedColor) {
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.parseColor("#800080"); // Purple
            case 5:
                return Color.parseColor("#FFA500"); // Orange
            default:
                return Color.WHITE; // Default to white if no color is matched
        }
    }
}
