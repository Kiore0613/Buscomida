package com.example.buscomida.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.buscomida.R;
import com.example.buscomida.about.AboutFragment;
import com.example.buscomida.category.CategoryFragment;
import com.example.buscomida.near.NearFragment;
import com.example.buscomida.preference.PreferenceFragment;
import com.example.buscomida.preference.SharedPref;
import com.example.buscomida.restaurant.RestaurantFragment;
import com.example.buscomida.search.SearchFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        navigationView.setItemTextColor(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.text_white, null)));
        navigationView.setItemIconTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null)));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();


                switch (menuItem.getItemId()) {

                    case R.id.navigation_search:
                        SearchFragment search = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, search).commit();
                        setTitle("Buscar");
                        break;

                    case R.id.navigation_restaurants:
                        RestaurantFragment restaurant = new RestaurantFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, restaurant).commit();
                        setTitle("Restaurantes");
                        break;

                    case R.id.navigation_near:
                        NearFragment container = new NearFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, container).commit();
                        Toast.makeText(getApplicationContext(), "Cerca de mi", Toast.LENGTH_LONG).show();
                        setTitle("Cerca de mi");
                        break;

                    case R.id.navigation_categories:
                        CategoryFragment category = new CategoryFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, category).commit();
                        setTitle("Categorias");
                        break;

                    case R.id.navigation_preferences:
                        PreferenceFragment preference = new PreferenceFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, preference).commit();
                        setTitle("Preferencias");
                        break;

                    case R.id.navigation_about:
                        AboutFragment about = new AboutFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, about).commit();
                        setTitle("Acerca de");
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.toolbar_preferences:
                Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
                PreferenceFragment preference = new PreferenceFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, preference).commit();
                setTitle("Preferencias");
                return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
