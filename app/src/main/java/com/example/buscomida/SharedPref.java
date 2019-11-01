package com.example.buscomida;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences mySharedPref ;
    private final static String LOGIN = "Login";
    private final static String NIGHT = "Night";

    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }
    // this method will save the nightMode State : True or False
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean(NIGHT,state);
        editor.apply();
    }
    // this method will load the Night Mode State
    public Boolean loadNightModeState (){

        return  mySharedPref.getBoolean(NIGHT,false);
    }

    public Boolean getKeepMeLoggedIn (){

        return mySharedPref.getBoolean(LOGIN,false);
    }
    public void setKeepMeLoggedIn (Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean(LOGIN,state);
        editor.apply();
    }



}

