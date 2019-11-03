package com.example.buscomida.main;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.buscomida.BaseAppCompat;
import com.example.buscomida.R;
import com.example.buscomida.SharedPref;
import com.example.buscomida.about.AboutFragment;
import com.example.buscomida.category.CategoryFragment;
import com.example.buscomida.near.NearFragment;
import com.example.buscomida.preference.PreferenceActivity;
import com.example.buscomida.search.SearchFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseAppCompat {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = new SharedPref(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setItemTextColor(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null)));
        navigationView.setItemIconTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null)));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();


                switch (menuItem.getItemId()) {

                    case R.id.navigation_search:
                        SearchFragment search = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, search).commit();
                        setTitle(getResources().getString(R.string.search));
                        break;

                    case R.id.navigation_near:
                        NearFragment container = new NearFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, container).commit();
                        setTitle(getResources().getString(R.string.near));
                        break;

                    case R.id.navigation_exit:

                        sharedPref.setKeepMeLoggedIn(false);
                        finish();
                        break;

                    case R.id.navigation_categories:
                        CategoryFragment category = new CategoryFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, category).commit();
                        setTitle(getResources().getString(R.string.categories));
                        break;


                    case R.id.navigation_about:
                        AboutFragment about = new AboutFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, about).commit();
                        setTitle(getResources().getString(R.string.about));
                }
                return true;
            }
        });

        CategoryFragment category = new CategoryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, category).commit();
        setTitle(getResources().getString(R.string.categories));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.toolbar_preferences) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
