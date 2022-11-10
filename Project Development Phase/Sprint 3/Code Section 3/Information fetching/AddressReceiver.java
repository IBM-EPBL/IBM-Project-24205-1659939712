package com.example.covid_19alertapp.extras;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.covid_19alertapp.activities.ShowMatchedLocationsActivity;

public class AddressReceiver extends ResultReceiver {


    // for interface
    private AddressView view;


    private static final String GEO_ADDRESS = "geo_address";
    private static final String GEO_LOCATION = "geo_location";
    private static final String GEO_RECEIVER = "geo_receiver";
    private static final String LIST_POSITION = "position@list";

    public AddressReceiver(Handler handler, AddressView view) {

        super(handler);
        this.view = view;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(resultData==null){
            Log.d(LogTags.Address_TAG, "onReceiveResult: null resultData");
            return;
        }

        // receive data from the FetchAddress
        String addressOutput = resultData.getString(GEO_ADDRESS);
        int listPosition = resultData.getInt(LIST_POSITION);

        if(addressOutput==null || resultCode==FetchAddress.getGeoFailure())
            addressOutput = "no address available (tap to see in map)";

        Log.d(LogTags.Address_TAG,"onReceiveResult: address received = "+addressOutput);

        view.updateAddress(addressOutput, listPosition);


    }

    public void startAddressFetchService(Activity activity, double latitude, double longitude, int listPosition){

        Location location = new Location("dummy-provider");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        Intent intent = new Intent(activity.getApplicationContext(), FetchAddress.class);
        intent.putExtra(GEO_LOCATION, location);
        intent.putExtra(GEO_RECEIVER, this);
        intent.putExtra(LIST_POSITION, listPosition);

        Log.d(LogTags.Address_TAG, "startAddressFetchService: starting address service for position = "+listPosition);

        activity.startService(intent);
    }

    public interface AddressView{

        void updateAddress(String address, int listPosition);

    }

}
