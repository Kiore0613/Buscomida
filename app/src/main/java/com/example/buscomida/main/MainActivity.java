package com.example.buscomida.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.example.buscomida.BaseAppCompat;
import com.example.buscomida.R;
import com.example.buscomida.SharedPref;
import com.example.buscomida.about.AboutFragment;
import com.example.buscomida.category.CategoryFragment;
import com.example.buscomida.login.LoginActivity;
import com.example.buscomida.near.NearFragment;
import com.example.buscomida.preference.PreferenceActivity;
import com.example.buscomida.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseAppCompat {

    Toolbar toolbar;
    SharedPref sharedPref;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = new SharedPref(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,
                    new CategoryFragment()).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {

                        case R.id.navigation_search:
                            selectedFragment = new SearchFragment();
                            break;

                        case R.id.navigation_near:
                            selectedFragment = new NearFragment();
                            break;

                        case R.id.navigation_categories:
                            selectedFragment = new CategoryFragment();

                            break;


                        case R.id.navigation_about:
                            selectedFragment = new AboutFragment();

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,
                            selectedFragment).commit();
                    return true;
                }


            };


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

        switch (item.getItemId()) {
            case R.id.toolbar_preferences:
                Intent intent = new Intent(this, PreferenceActivity.class);
                startActivity(intent);
                break;

            case R.id.toolbar_exit:
                sharedPref.setKeepMeLoggedIn(false);
                Intent exit = new Intent(this, LoginActivity.class);
                exit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exit);
                break;

        }

        return super.onOptionsItemSelected(item);


    }
}
