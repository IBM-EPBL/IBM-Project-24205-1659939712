package com.example.covid_19alertapp.models;

public class UserInfoData {

    String name,dob,workLatLng = "",homeLatLng, contactNumber, homeAddress = "", workAddress = "";

    public UserInfoData() {
    }

    public UserInfoData(String name, String dob, String workLatLng, String homeLatLng, String contactNumber, String homeAddress, String workAddress) {
        this.name = name;
        this.dob = dob;
        this.workLatLng = workLatLng;
        this.homeLatLng = homeLatLng;
        this.contactNumber = contactNumber;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public UserInfoData(String name, String dob, String homeLatLng, String contactNumber, String homeAddress) {
        this.name = name;
        this.dob = dob;
        this.homeLatLng = homeLatLng;
        this.contactNumber = contactNumber;
        this.homeAddress = homeAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWorkLatLng() {
        return workLatLng;
    }

    public void setWorkLatLng(String workLatLng) {
        this.workLatLng = workLatLng;
    }

    public String getHomeLatLng() {
        return homeLatLng;
    }

    public void setHomeLatLng(String homeLatLng) {
        this.homeLatLng = homeLatLng;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
}
