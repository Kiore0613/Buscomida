package com.example.buscomida.preference;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.buscomida.R;
import com.example.buscomida.main.MainActivity;

public class PreferenceFragment extends Fragment {

    View view;
    Switch switchDarkMode;
    SharedPref sharedPref;

    private final static String TAG = "FeedActivity";

    public PreferenceFragment() {
        // Required empty public constructor
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPref = new SharedPref(getContext());
        if(sharedPref.loadNightModeState()==true) {
            getContext().setTheme(R.style.DarkTheme);
        }
        else  getContext().setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        switchDarkMode = view.findViewById(R.id.switch_dark);
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

     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPref = new SharedPref(getContext());
        if (sharedPref.loadNightModeState() == true) {
            getContext().setTheme(R.style.DarkTheme);
        } else getContext().setTheme(R.style.AppTheme);
        view = inflater.inflate(R.layout.fragment_preference, container, false);
        switchDarkMode = view.findViewById(R.id.switch_dark);
        if (sharedPref.loadNightModeState() == true) {
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

    public void restartApp() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();


    }


}
