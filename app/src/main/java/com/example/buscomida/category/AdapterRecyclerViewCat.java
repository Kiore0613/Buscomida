package com.example.buscomida.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buscomida.R;
import com.example.buscomida.apiFiles.Restaurant;

import java.util.ArrayList;

public class AdapterRecyclerViewCat
        extends RecyclerView.Adapter<AdapterRecyclerViewCat.ViewHolderCategory>
        implements View.OnClickListener{

    private ArrayList<Restaurant> listRestaurant;

    public AdapterRecyclerViewCat(ArrayList<Restaurant> listRestaurant) {
        this.listRestaurant = listRestaurant;
    }

    private View.OnClickListener listener;

    @NonNull
    @Override
    public ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_search, parent, false);

        view.setOnClickListener(this);

        return new AdapterRecyclerViewCat.ViewHolderCategory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewCat.ViewHolderCategory holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolderCategory extends RecyclerView.ViewHolder {
        public ViewHolderCategory(@NonNull View itemView) {
            super(itemView);
        }
    }
}
