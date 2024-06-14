package com.project.pantry.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PantrySharedPreference {

    private SharedPreferences sharedPref;

    public PantrySharedPreference(Context context) {
        sharedPref = context.getSharedPreferences(Helper.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public SharedPreferences getInstanceOfSharedPreference(){
        return sharedPref;
    }

    //Save user information
    public void setUserData(String userData){
        sharedPref.edit().putString(Helper.USER_DATA, userData).apply();
    }

    public String getUserData(){
        return sharedPref.getString(Helper.USER_DATA, "");
    }



}
