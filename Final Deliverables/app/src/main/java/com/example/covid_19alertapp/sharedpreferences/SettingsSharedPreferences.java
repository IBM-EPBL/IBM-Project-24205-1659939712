package com.example.covid_19alertapp.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.covid_19alertapp.extras.Constants;

import static android.content.Context.MODE_PRIVATE;

public abstract class SettingsSharedPreferences {

    private static SharedPreferences settings;

    public static boolean getLocationTrackerState(Context context){

        settings = context.getSharedPreferences(Constants.LOCATION_SETTINGS_SHARED_PREFERENCES, MODE_PRIVATE);

        return settings.getBoolean(Constants.location_tracker_state, false);


    }

    public static void setLocationTrackerState(Context context, boolean state){

        settings = context.getSharedPreferences(Constants.LOCATION_SETTINGS_SHARED_PREFERENCES, MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Constants.location_tracker_state, state);
        editor.apply();
    }

}
