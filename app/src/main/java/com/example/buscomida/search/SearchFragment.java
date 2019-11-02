package com.example.buscomida.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buscomida.apiFiles.BuscomidaApi;
import com.example.buscomida.R;
import com.example.buscomida.apiFiles.Restaurant;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    View view;
    HttpLoggingInterceptor loggingInterceptor;
    OkHttpClient.Builder httpClient;
    RecyclerView recyclerViewRestaurant;
    SearchView searchViewRestaurant;
    public SearchFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        searchViewRestaurant = view.findViewById(R.id.search_restaurant);
        recyclerViewRestaurant = view.findViewById(R.id.rv_restaurant);

        retrofitData();
        return view;
    }


    public void retrofitData(){

        searchViewRestaurant.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4:5000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
                BuscomidaApi buscomidaApi = retrofit.create(BuscomidaApi.class);
                Call<List<Restaurant>> call = buscomidaApi.getRestaurantes(query);

                call.enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                        List<Restaurant> restaurantsList = response.body();
                        recyclerViewRestaurant.setLayoutManager(new LinearLayoutManager(getContext()));
                        AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(new ArrayList<>(restaurantsList));
                        recyclerViewRestaurant.setAdapter(adapterRecyclerView);
                        recyclerViewRestaurant.addItemDecoration(
                                new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}
