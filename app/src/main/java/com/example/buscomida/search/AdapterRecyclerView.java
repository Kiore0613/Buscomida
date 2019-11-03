package com.example.buscomida.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buscomida.R;
import com.example.buscomida.apiFiles.Restaurant;

import java.util.ArrayList;

public class AdapterRecyclerView
        extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolderRestaurant>
        implements View.OnClickListener {

    private ArrayList<Restaurant> listRestaurant;
    private View.OnClickListener listener;


    public AdapterRecyclerView(ArrayList<Restaurant> listRestaurant) {
        this.listRestaurant = listRestaurant;

    }

    @NonNull
    @Override
    public ViewHolderRestaurant onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_search, parent, false);

        view.setOnClickListener(this);

        return new ViewHolderRestaurant(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRestaurant holder, final int position) {
        holder.textViewRestaurante.setText(listRestaurant.get(position).getNombreRestaurante());
        holder.textViewEspec.setText(listRestaurant.get(position).getEspecialidadRestaurante());
        holder.textViewCat.setText(listRestaurant.get(position).getNombreCategoria());
        holder.textViewDirecc.setText(listRestaurant.get(position).getDireccionRestaurante());

    }

    @Override
    public int getItemCount() {
        return listRestaurant.size();

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderRestaurant extends RecyclerView.ViewHolder {

        TextView textViewRestaurante, textViewEspec, textViewCat, textViewDirecc;

        public ViewHolderRestaurant(View itemView) {
            super(itemView);

            textViewRestaurante = itemView.findViewById(R.id.tv_nombre_restaurante);
            textViewEspec = itemView.findViewById(R.id.tv_espec_restaurante);
            textViewCat = itemView.findViewById(R.id.tv_cat_restaurante);
            textViewDirecc = itemView.findViewById(R.id.tv_direcc_restaurante);

        }
    }

}