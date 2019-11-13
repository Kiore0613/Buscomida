package com.example.buscomida.restaurant;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buscomida.R;
import com.example.buscomida.SharedPref;
import com.example.buscomida.apiFiles.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantFragment extends Fragment implements OnMapReadyCallback {

    private TextView textViewNameRestaurant;
    private TextView textViewEspecRestaurant;
    private TextView textViewCatRestaurant;
    private TextView textViewDirRestaurant;

    private Restaurant restaurant;

    private SharedPref sharedPref;

    private final static String OBJ = "obj";

    public RestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPref = new SharedPref(getContext());
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_restaurant);

        mapFragment.getMapAsync(this);

        textViewNameRestaurant = view.findViewById(R.id.tv_name_restaurant);
        textViewEspecRestaurant = view.findViewById(R.id.tv_espec_restaurant);
        textViewCatRestaurant = view.findViewById(R.id.tv_cat_restaurant);
        textViewDirRestaurant = view.findViewById(R.id.tv_direc_restaurant);
        sinopsis();
        return view;

    }

    private void sinopsis() {
        restaurant = getArguments().getParcelable(OBJ);

        String nameRestaurant = restaurant.getNombreRestaurante();
        String especRestaurant = restaurant.getEspecialidadRestaurante();
        String direcRestaurant = restaurant.getDireccionRestaurante();
        String catRestaurant = restaurant.getNombreCategoria();

        textViewNameRestaurant.setText(nameRestaurant);
        textViewEspecRestaurant.setText(especRestaurant);
        textViewCatRestaurant.setText(catRestaurant);
        textViewDirRestaurant.setText(direcRestaurant);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng position = new LatLng(Double.parseDouble(restaurant.getLatRestaurante()), Double.parseDouble(restaurant.getLogRestaurante()));
        googleMap.addMarker(new MarkerOptions().position(position)
                .flat(true)
                .title(restaurant.getNombreRestaurante()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        if (sharedPref.getMap()) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        } else {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        }
    }
}
