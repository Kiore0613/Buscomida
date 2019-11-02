package com.example.buscomida.preference;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buscomida.R;
import com.example.buscomida.SharedPref;
import com.example.buscomida.main.MainActivity;

public class PreferenceFragment extends Fragment {

    private SharedPref sharedPref;


    public PreferenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPref = new SharedPref(getContext());

        //dark mode
        if (sharedPref.loadNightModeState()) {
            getContext().setTheme(R.style.DarkTheme);
        } else getContext().setTheme(R.style.AppTheme);

        View view = inflater.inflate(R.layout.fragment_preference, container, false);

        Switch switchDarkMode = view.findViewById(R.id.switch_dark);
        Switch switchMap = view.findViewById(R.id.switch_map);
        Switch swichLite = view.findViewById(R.id.switch_lite);

        //switch Map
        if (sharedPref.getMap()) {
            switchMap.setChecked(true);
        }

        switchMap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    sharedPref.setMap(true);
                    restartApp();
                } else {
                    sharedPref.setMap(false);
                    restartApp();
                }
            }
        });

        //switch Lite Map

        if (sharedPref.getLiteMap()) {
            swichLite.setChecked(true);
        }

        swichLite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    sharedPref.setLiteMap(true);
                    restartApp();
                } else {
                    sharedPref.setLiteMap(false);
                    restartApp();
                }
            }
        });

        //switch Dark Mode
        if (sharedPref.loadNightModeState()) {
            switchDarkMode.setChecked(true);
        }

        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    sharedPref.setNightModeState(true);
                    restartApp();

                } else {
                    sharedPref.setNightModeState(false);
                    restartApp();
                }
            }
        });


        return view;

    }


    private void restartApp() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();


    }


}
