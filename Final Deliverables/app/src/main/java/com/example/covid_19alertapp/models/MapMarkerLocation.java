package com.example.covid_19alertapp.models;

/*
model for MyLocationMapsActivity
 */

public class MapMarkerLocation {

    private double latitude, longitude;

    private String meaningfulDateTime;

    private String rawLatLon, rawDateTime;

    public MapMarkerLocation() {
    }

    public MapMarkerLocation(String latLon, String dateTime){

        this.rawLatLon = latLon;
        this.rawDateTime = dateTime;

        // latLon = diagonal latLng point separated by ','
        String[] splitLL = latLon.split(",");

        // get the middle point
        this.latitude = ( Double.valueOf(splitLL[0]) + Double.valueOf(splitLL[2]) ) / 2;
        this.longitude = ( Double.valueOf(splitLL[1]) + Double.valueOf(splitLL[3]) ) / 2;

        // dateTime = month-date-hour
        String[] splitDateTime = dateTime.split("-");

        this.meaningfulDateTime =
                month(Integer.parseInt(splitDateTime[0])) +
                " "+splitDateTime[1] +
                ", "+time(Integer.parseInt(splitDateTime[2]));

    }


    private String time(int time) {

        if(time==0)
            return "12AM";

        if(time<12)
            return time+"AM";
        else
            return (time-12)+"PM";

    }

    private String month(int month) {

        switch (month){

            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";

            default:
                return "Unknown month";
        }

    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMeaningfulDateTime() {
        return meaningfulDateTime;
    }

    public void setMeaningfulDateTime(String meaningfulDateTime) {
        this.meaningfulDateTime = meaningfulDateTime;
    }

    public String getRawLatLon() {
        return rawLatLon;
    }

    public void setRawLatLon(String rawLatLon) {
        this.rawLatLon = rawLatLon;
    }

    public String getRawDateTime() {
        return rawDateTime;
    }

    public void setRawDateTime(String rawDateTime) {
        this.rawDateTime = rawDateTime;
    }

    @Override
    public String toString() {
        return "MapMarkerLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", meaningfulDateTime='" + meaningfulDateTime + '\'' +
                ", rawLatLon='" + rawLatLon + '\'' +
                ", rawDateTime='" + rawDateTime + '\'' +
                '}';
    }
}
