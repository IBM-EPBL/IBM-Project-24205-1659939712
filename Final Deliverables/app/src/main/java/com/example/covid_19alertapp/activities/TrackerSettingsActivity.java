package com.example.covid_19alertapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.extras.Constants;
import com.example.covid_19alertapp.extras.LocationFetch;
import com.example.covid_19alertapp.extras.LogTags;
import com.example.covid_19alertapp.extras.Notifications;
import com.example.covid_19alertapp.extras.Permissions;
import com.example.covid_19alertapp.services.BackgroundLocationTracker;

public class TrackerSettingsActivity extends AppCompatActivity {
/*
settings (currently only contains location on/off)
 */

    Button home_btn;
    Switch notification_switch;
    private static boolean switch_status;

    // for location permission
    private Permissions permissions;
    private static final String[] permissionStrings = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_settings);

        home_btn= findViewById(R.id.home_button_settings);

        //start notification channel(do this is MainActivity
        Notifications.createNotificationChannel(this);

        notification_switch = findViewById(R.id.notification_switch);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        notification_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_preferences(notification_switch.isChecked());
                if(notification_switch.isChecked())
                {
                    try {

                        LocationFetch.checkDeviceLocationSettings(TrackerSettingsActivity.this);
                        if(LocationFetch.isLocationEnabled) {
                            // location is enabled
                            // start tracker service
                            Log.d(LogTags.Location_TAG, "onClick: location found enabled");

                            // start BackgroundLocationTracker
                            startTrackerService();
                        }

                        else{
                            // location is not enabled
                            Log.d(LogTags.Location_TAG, "onClick: location found disabled");

                            notification_switch.setChecked(false);
                            Toast.makeText(getApplicationContext(), "Turn on location or press again please", Toast.LENGTH_LONG)
                                    .show();
                            save_preferences(false);
                        }
                    }catch (Exception e){

                        // set switch off
                        notification_switch.setChecked(false);

                        // set shared preferences false
                        save_preferences(false);

                        // most probable reason for error is permission not granted
                        promptPermissions();

                        Log.d(LogTags.TrackerSettings_TAG, "onClick: error starting background location service! permission taken?");
                    }
                }
                else
                {
                    try {
                        // stop location tracker
                        stopService(new Intent(getApplicationContext(),BackgroundLocationTracker.class));

                    }catch (Exception e){
                        Log.d(LogTags.TrackerSettings_TAG, "onClick: error occured!");
                    }
                }
            }
        });
        loadData();
        updateViews();
    }

    private void startTrackerService(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(getApplicationContext(), BackgroundLocationTracker.class));
            Log.d(LogTags.TrackerSettings_TAG, "onClick: newer version phones foreground service stared");
        } else
            startService(new Intent(getApplicationContext(), BackgroundLocationTracker.class));

    }

    private void promptPermissions() {

        permissions = new Permissions(this, permissionStrings, Constants.PERMISSION_CODE);

        if(!permissions.checkPermissions())
            permissions.askPermissions();

    }


    public void save_preferences(boolean state)
    {
        SharedPreferences sharedPreferences =
                getSharedPreferences(Constants.LOCATION_SETTINGS_SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.location_tracker_state,state);
        editor.apply();
    }
    public void loadData()
    {
        SharedPreferences sharedPreferences =
                getSharedPreferences(Constants.LOCATION_SETTINGS_SHARED_PREFERENCES, MODE_PRIVATE);
        switch_status = sharedPreferences.getBoolean(Constants.location_tracker_state,false);
        updateViews();
    }

    public void updateViews()
    {
        notification_switch.setChecked(switch_status);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case Constants.LOCATION_CHECK_CODE:
                // user input from the dialogbox showed after checkLocation()

                if(Activity.RESULT_OK == resultCode){
                    // user picked yes
                    Log.d(LogTags.Location_TAG, "onActivityResult: user picked yes. starting background location tracker");

                    startTrackerService();

                    // save settings preferences
                    save_preferences(true);
                    // set LocationFetch boolean
                    LocationFetch.isLocationEnabled = true;

                    //set the settings switch UI to true
                    notification_switch.setChecked(true);
                }

                else if(Activity.RESULT_CANCELED == resultCode){
                    // user picked no
                    Log.d(LogTags.Location_TAG, "onActivityResult: user picked no. setting boolean and preference to false");

                    save_preferences(false);
                    LocationFetch.isLocationEnabled = false;
                }

                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //resolve unresolved permissions

        switch (requestCode){

            case Constants.PERMISSION_CODE:

                try {
                    this.permissions.resolvePermissions(permissions, grantResults);
                }catch (Exception e){
                    Log.d(LogTags.Permissions_TAG, "onRequestPermissionsResult: "+e.getMessage());
                }

                break;

        }

    }

}
