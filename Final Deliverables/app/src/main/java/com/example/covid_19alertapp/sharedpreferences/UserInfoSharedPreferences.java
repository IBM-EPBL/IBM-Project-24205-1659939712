package com.example.covid_19alertapp.sharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.covid_19alertapp.extras.Constants;

import static android.content.Context.MODE_PRIVATE;

public abstract class UserInfoSharedPreferences {


    private static SharedPreferences userInfo;

    public static String getUid(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.uid_preference, "");

    }

    public static String getUsername(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.username_preference, "");

    }

    public static String getDob(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.user_dob_preference, "");

    }

    public static String getHomeLatLng(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.user_home_address_latlng_preference, "");

    }

    public static String getWorkLatLng(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.user_work_address_latlng_preference, "");

    }

    public static String getHomeAddress(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.user_home_address_preference, "");

    }

    public static String getWorkAddress(Context context){

        userInfo = context.getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES, MODE_PRIVATE);

        return userInfo.getString(Constants.user_work_address_preference, "");

    }

}
