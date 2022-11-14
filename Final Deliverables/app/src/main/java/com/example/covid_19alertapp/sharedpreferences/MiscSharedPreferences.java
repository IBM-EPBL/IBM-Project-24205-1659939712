package com.example.covid_19alertapp.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.covid_19alertapp.extras.Constants;

import static android.content.Context.MODE_PRIVATE;

public abstract class MiscSharedPreferences {

    private static SharedPreferences misc;

    public static boolean getUploadStatus(Context context){

        misc = context.getSharedPreferences(Constants.MISC_SHARED_PREFERENCES, MODE_PRIVATE);

        return misc.getBoolean(Constants.upload_state, false);


    }

    public static void setUploadStatus(Context context, boolean state){

        misc = context.getSharedPreferences(Constants.MISC_SHARED_PREFERENCES, MODE_PRIVATE);

        SharedPreferences.Editor editor = misc.edit();
        editor.putBoolean(Constants.upload_state, state);
        editor.apply();
    }

    public static boolean getBgWorkerStatus(Context context){

        misc = context.getSharedPreferences(Constants.MISC_SHARED_PREFERENCES, MODE_PRIVATE);

        return misc.getBoolean(Constants.bg_worker_state, false);


    }

    public static void setBgWorkerStatus(Context context, boolean state){

        misc = context.getSharedPreferences(Constants.MISC_SHARED_PREFERENCES, MODE_PRIVATE);

        SharedPreferences.Editor editor = misc.edit();
        editor.putBoolean(Constants.bg_worker_state, state);
        editor.apply();
    }

}
