package com.example.grocerylistapp;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class listFragment extends Fragment {
    private String item;

    public listFragment(){}

    public static listFragment newInstance(String item) {
        listFragment fragment = new listFragment();
        Bundle args = new Bundle();
        args.putString("item", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getString("item");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_layout, container, false);
        TextView itemNameTextView = view.findViewById(R.id.itemName);

        if (item != null) {
            itemNameTextView.setText(item);
            CardView itemCardView = view.findViewById(R.id.itemCardView);
        }

        return view;
    }
}
