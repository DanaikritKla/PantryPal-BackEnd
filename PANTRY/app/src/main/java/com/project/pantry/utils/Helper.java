package com.project.pantry.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {

    public static final String PUBLIC_FOLDER = "http://192.168.0.109/pantry/android/";
    public static final String PUBLIC_FOLDER_IMAGE_FOOD = "http://192.168.0.109/pantry/food/";
    public static final String PATH_TO_SAVE_REGISTER = PUBLIC_FOLDER + "sign_up.php";
    public static final String PATH_TO_SERVER_LOGIN = PUBLIC_FOLDER + "sign_in.php";
    public static final String PATH_TO_SERVER_ALL_MENUS = PUBLIC_FOLDER + "all_menu.php";

    public static final String NEW_ACCOUNT = " ";
    public static final int MY_SOCKET_TIMEOUT_MS = 5000;
    public static final String ROUTE_ID = "ROUTE_ID";
    public static final String USER_DATA = "USER_DATA";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String DELIVERY_ADDRESS = "DELIVERY_ADDRESS";
    public static final String SHARED_PREF = "SHARED_PREFERENCE";


    public static final String CLIENT_ID = "";

    public static final int MINIMUM_LENGTH = 5;


    public static void displayErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isValidEmail(String email){
        return email.contains("@");
    }

    public static String dateFormatting(String dateInString){
        SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date stringDate = null;
        try {
            stringDate = sdfDestination.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH).format(stringDate);
    }
}
