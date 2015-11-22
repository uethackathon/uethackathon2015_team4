package com.thangtv.surrounding.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thangtv.surrounding.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Var {
    //radius for finding neary posts
    public static int radius = 4;

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

    public static void saveUser(Context context, String key, User object) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext())
                .edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(key, json);
        editor.apply();
    }

    //get data from SharedPrefernces
    public static String get(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, null);
    }

    public static User getUser(Context context, String key) {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = settings.getString(key, "");
        User obj = (User) gson.fromJson(json, User.class);
        return obj;
    }

    public static void clearUser(Context context) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext())
                .edit();
        editor.remove(Const.KEY_USER);
    }

    public static List<String> selectedCategoryIDs;

    public static User currentUser;

    public static Calendar timestampToCalendar(String time) {
        long timestampLong = Long.parseLong(time) * 1000;
        Date d = new Date(timestampLong);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c;
    }

    public static String calendarToTimestamp(String calendar){
        try{
            String dateString = "";
            String[] splitsTime = calendar.split("/");
            if (splitsTime[0].length()!=2)
                dateString += "0"+splitsTime[0]+"/";
            else
                dateString += splitsTime[0]+"/";

            if (splitsTime[1].length()!=2)
                dateString += "0"+splitsTime[1]+"/";
            else
                dateString += splitsTime[1]+"/";

            dateString += splitsTime[2];

            DateFormat sdf = new SimpleDateFormat("dd/mm/yyyyy");
            Date date = sdf.parse(dateString);

            return date.getTime() + "";
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

}
