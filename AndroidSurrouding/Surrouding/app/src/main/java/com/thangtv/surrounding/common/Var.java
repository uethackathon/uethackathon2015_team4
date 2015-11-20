package com.thangtv.surrounding.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by uendno on 14-Nov-15.
 */
public class Var {

    //show Toast
    public static void showToast(Context context, String sms) {
        Toast.makeText(context, sms, Toast.LENGTH_SHORT).show();
    }

    //save data to SharedPrefernces
    public static void save(Context context, String key, String value) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext())
                .edit();
        editor.putString(key, value);
        editor.apply();
    }

    //get data from SharedPrefernces
    public static String get(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, null);
    }
}
