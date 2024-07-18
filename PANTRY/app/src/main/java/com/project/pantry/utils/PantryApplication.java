package com.project.pantry.utils;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.pantry.entities.LoginObject;

public class PantryApplication extends Application {
    private Gson gson;
    private GsonBuilder builder;

    private PantrySharedPreference shared;

    @Override
    public void onCreate() {
        super.onCreate();
        builder = new GsonBuilder();
        gson = builder.create();
        shared = new PantrySharedPreference(getApplicationContext());
    }




    public PantrySharedPreference getShared(){
        return shared;
    }

    public Gson getGsonObject(){
        return gson;
    }

    public LoginObject getLoginUser(){
        Gson mGson = getGsonObject();
        String storedUser = getShared().getUserData();
        return mGson.fromJson(storedUser, LoginObject.class);
    }

}
