package com.example.buscomida.preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.buscomida.R;
import com.example.buscomida.main.MainActivity;

public class PreferenceActivity extends AppCompatActivity {

    Switch switchDarkMode;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
          setTheme(R.style.DarkTheme);
        }
        else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        switchDarkMode = findViewById(R.id.switch_dark);
        if (sharedPref.loadNightModeState()==true) {
            switchDarkMode.setChecked(true);
        }

        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    sharedPref.setNightModeState(true);
                    restartApp();

                }
                else{
                    sharedPref.setNightModeState(false);
                    restartApp();

                }
            }
        });
    }
    public void restartApp(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }
}
