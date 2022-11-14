package com.example.covid_19alertapp.models;

import com.google.firebase.database.Exclude;

public class InfectedLocations {
/*
 firebase model

 structure:
    ->key
        ->dateTime
            ->count
 */

    @Exclude
    private String key = null;

    private long count = 0;
    private String address = null;

    @Exclude
    private String dateTime;

    public InfectedLocations() {
        /*
        required for firebase
         */
    }


    public InfectedLocations(String key, long count, String dateTime) {

        /*** REPLACE DECIMAL POINTS TO '@' SYMBOL
         * FIREBASE DOESN'T ACCEPT DECIMAL POINTS AS KEY*/
        this.key = key.replaceAll("\\.","@");

        this.count = count;
        this.dateTime = dateTime;
    }

    @Exclude
    public boolean allFieldsSet(){
        return key!=null && count!=0 && dateTime!=null;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public String getDateTime() { return dateTime; }

    @Exclude
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

}
