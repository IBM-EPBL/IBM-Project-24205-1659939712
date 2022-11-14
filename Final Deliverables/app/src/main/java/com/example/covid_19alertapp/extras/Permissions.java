package com.example.covid_19alertapp.extras;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.covid_19alertapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permissions {

    private Activity activity;

    private String[] appPermissions;

    private int PERMISSION_REQUEST_CODE;

    private List<String> permissionsRequired = new ArrayList<>();

    public Permissions(Activity activity, String[] appPermissions, int PERMISSION_REQUEST_CODE) {
        this.activity = activity;
        this.appPermissions = appPermissions;
        this.PERMISSION_REQUEST_CODE = PERMISSION_REQUEST_CODE;
    }

    public int getPERMISSION_REQUEST_CODE() {
        return PERMISSION_REQUEST_CODE;
    }

    public boolean checkPermissions(){

        /*
        return true if all permissions are granted
        else return false and store unresolved permissions in list
        call askPermissions() and resolvePermissions()
        after this method is invoked
         */

        //clear out permissionRequired arraylist
        permissionsRequired = new ArrayList<>();

        //get required permissions into permissionsRequired arraylist
        for(String permission: appPermissions){
            if(ContextCompat.checkSelfPermission(this.activity.getApplicationContext(),permission)
                    == PackageManager.PERMISSION_DENIED
            ){
                this.permissionsRequired.add(permission);
                Log.d(LogTags.Permissions_TAG, "checkPermissions: "+permission+" not granted");
            }
        }

        if(!this.permissionsRequired.isEmpty())
            return false;

        Log.d(LogTags.Permissions_TAG, "checkPermissions: all permissions granted hurrah!");
        return true;

    }



    public void askPermissions(){
        //ask for permission initially

        ActivityCompat.requestPermissions(
                activity,
                this.permissionsRequired.toArray(new String[this.permissionsRequired.size()]),
                PERMISSION_REQUEST_CODE
        );
    }


    public void resolvePermissions(String[] permissions, int[] grantResults){

        HashMap<String, Integer> permissionResult = new HashMap<>();

        for (int i = 0; i < grantResults.length; i++) {
            //get the still not allowed permissions

            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                permissionResult.put(permissions[i], grantResults[i]);
                Log.d(LogTags.Permissions_TAG, "resolvePermissions: denied permission = "+permissions[i]+" grant result = "+grantResults[i]);
            }
        }

        if (!permissionResult.isEmpty()) {

            String alertBoxMessage = this.activity.getString(R.string.permissions_dialogbox_explanation);

            Log.d(LogTags.Permissions_TAG, "resolvePermissions: alert box message = " + alertBoxMessage);

            for (Map.Entry<String, Integer> entry : permissionResult.entrySet()) {
                //request permission one by one with proper explanation

                String permission = entry.getKey();
                int resultCode = entry.getValue();
                Log.d(LogTags.Permissions_TAG, "resolvePermissions: permission = " + permission + " result code = " + resultCode);

                if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, permission)) {
                    //user denied collective permission once but hasn't picked never allow

                    Permissions.this.alertDialog(

                            alertBoxMessage,

                            //positive listener
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    Log.d(LogTags.Permissions_TAG, "onClick: dialog.dismiss() called");

                                    Permissions.this.checkPermissions();
                                    Permissions.this.askPermissions();
                                }
                            },

                            //negative listener
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Permissions.this.activity.finish();
                                }
                            }

                    );

                } else if(resultCode == PackageManager.PERMISSION_DENIED){
                    //user has picked never allow

                    Log.d(LogTags.Permissions_TAG, "resolvePermissions: never allow disos kerreee!!!!!!");
                    //TODO: show user dialog box then prompt user to go to settings and allow
                }

            }
        }

        else {
            Log.d(LogTags.Permissions_TAG, "resolvePermissions: All permissions granted");

            this.permissionsRequired.clear();
        }
    }



    private void alertDialog(String message, DialogInterface.OnClickListener positiveListener,
                             DialogInterface.OnClickListener negativeListener)
    {
        //TODO:common alert box
        Log.d(LogTags.Permissions_TAG, "alertDialog: creating alert-box");

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle((R.string.permissions_dialogbox_title))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton( (R.string.permissions_dialogbox_positive) , positiveListener)
                .setNegativeButton( (R.string.permissions_dialogbox_negative) , negativeListener)
        ;


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
