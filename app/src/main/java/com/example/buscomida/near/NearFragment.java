package com.example.buscomida.near;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buscomida.R;
import com.example.buscomida.SharedPref;
import com.example.buscomida.apiFiles.BuscomidaApi;
import com.example.buscomida.apiFiles.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private GoogleMap map;
    private Marker markerPais;
    private SharedPref sharedPref;

    RelativeLayout relativeLayout;

    public NearFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPref = new SharedPref(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_near, container, false);

        relativeLayout = view.findViewById(R.id.near_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        retrofitData();
        mapFragment.getMapAsync(this);

        return view;
    }

    private void retrofitData() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        BuscomidaApi buscomidaApi = retrofit.create(BuscomidaApi.class);
        Call<List<Restaurant>> call = buscomidaApi.getRestaurantes();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(@NonNull Call<List<Restaurant>> call, @NonNull Response<List<Restaurant>> response) {


                List<Restaurant> restaurantsList = response.body();

                for (Restaurant restaurant : restaurantsList) {

                    LatLng position = new LatLng(Double.parseDouble(restaurant.getLatRestaurante()), Double.parseDouble(restaurant.getLogRestaurante()));
                    map.addMarker(new MarkerOptions().position(position)
                            .flat(true)
                            .title(restaurant.getNombreRestaurante()));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 12));
                    map.setMyLocationEnabled(true);

                    if (sharedPref.getLiteMap()) {
                        Snackbar snackbar = Snackbar
                                .make(relativeLayout, getResources().getString(R.string.location), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        map.setMyLocationEnabled(false);
                        map.clear();
                    }


                    if (sharedPref.getMap()) {
                        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                    } else {
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                    }
                }
            }


            @Override
            public void onFailure(@NonNull Call<List<Restaurant>> call, @NonNull Throwable t) {
                Log.e("TAG1", "Error" + t.getMessage());

            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        double lattitude = location.getLatitude();
        double longitude = location.getLongitude();

        //Place current location marker
        LatLng latLng = new LatLng(lattitude, longitude);

        if (markerPais != null) {
            markerPais.setPosition(latLng);
        } else {
            markerPais = map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("I am here"));
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
    }
}


