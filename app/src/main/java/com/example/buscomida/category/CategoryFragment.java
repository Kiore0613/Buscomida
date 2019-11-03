package com.example.buscomida.category;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.buscomida.R;
import com.example.buscomida.apiFiles.BuscomidaApi;
import com.example.buscomida.apiFiles.Restaurant;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {


    private final static String OBJ = "obj";
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.pager);

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        BuscomidaApi buscomidaApi = retrofit.create(BuscomidaApi.class);
        Call<List<Restaurant>> call = buscomidaApi.getCategorias();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(@NonNull Call<List<Restaurant>> call, @NonNull Response<List<Restaurant>> response) {


                List<Restaurant> categoryList = response.body();

                for (Restaurant restaurant : categoryList) {

                    viewPagerAdapter.addFragment(new CatRecyclerFragment(), restaurant.getCategoriaNombre());
                    Toast.makeText(getContext(), restaurant.getNombreCategoria(), Toast.LENGTH_LONG).show();
                    viewPager.setAdapter(viewPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }


            @Override
            public void onFailure(@NonNull Call<List<Restaurant>> call, @NonNull Throwable t) {
                Log.e("TAG1", "Error" + t.getMessage());

            }
        });

        return view;
    }


}