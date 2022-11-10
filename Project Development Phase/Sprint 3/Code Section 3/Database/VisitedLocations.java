package com.example.covid_19alertapp.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class VisitedLocations {

    @PrimaryKey
    @NonNull
    private String conatainerDateTimeComposite;

    private long count;

    public VisitedLocations() {
        /*
        necessary for room(?)
         */
    }

    @Ignore
    public VisitedLocations(String conatainerDateTimeComposite, long count) {
        this.conatainerDateTimeComposite = conatainerDateTimeComposite;
        this.count = count;
    }

    @Ignore
    public String[] splitPrimaryKey(){
        /*
        returns 'latLon' and 'dateTime'
         */
        return conatainerDateTimeComposite.split("_");
    }

    @Ignore
    public String getATencodedlatlon(){
        /*
        return latlon in firebase KEY format
         */

        String[] splited = conatainerDateTimeComposite.split("_");

        return splited[0].replaceAll("\\.","@");

    }


    public void setConatainerDateTimeComposite(String conatainerDateTimeComposite) {
        this.conatainerDateTimeComposite = conatainerDateTimeComposite;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getConatainerDateTimeComposite() {
        return conatainerDateTimeComposite;
    }

    public long getCount() {
        return count;
    }

}vvvvvvvvvvv
