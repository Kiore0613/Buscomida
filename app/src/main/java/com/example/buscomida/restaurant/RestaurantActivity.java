package com.example.buscomida.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.buscomida.BaseAppCompat;
import com.example.buscomida.R;
import com.example.buscomida.apiFiles.Restaurant;

public class RestaurantActivity extends BaseAppCompat {

    Toolbar toolbar;

    final static String OBJ = "obj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        setTitle(getResources().getString(R.string.restaurants));
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sendData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;

    }

    private void sendData() {

        Intent intent = getIntent();


        Restaurant restaurant = intent.getParcelableExtra(OBJ);
        Bundle bundle = new Bundle();

        bundle.putParcelable(OBJ, restaurant);

        if (restaurant != null) {


            RestaurantFragment restaurantFragment = new RestaurantFragment();

            restaurantFragment.setArguments(bundle);

            restaurantFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_restaurant, restaurantFragment)
                    .commit();


        }
    }
}
