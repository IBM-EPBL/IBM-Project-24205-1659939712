package com.example.covid_19alertapp.activities;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.extras.AddressReceiver;
import com.example.covid_19alertapp.models.UserInfoData;
import com.example.covid_19alertapp.extras.Constants;
import com.example.covid_19alertapp.extras.LogTags;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfoFormActivity extends AppCompatActivity implements AddressReceiver.AddressView {


    EditText dobText,userName;
    TextView workAddress,homeAddress;
    Button save_profile;
    UserInfoData userInfoData;

    FirebaseDatabase database;
    DatabaseReference userInfoRef;
    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    String path="UserInfo";

    public static SharedPreferences userInfo;
    private String homeLatLng = "", workLatLng = "", homeAddressVariable = "", workAddressVariable = "";

    // address picker keys
    private static final int HOME_ADDRESS_PICKER = 829;
    private static final int WORK_ADDRESS_PICKER = 784;

    // address picker icon
    Drawable checkedIcon;

    // latLng to address fetcher
    AddressReceiver addressReceiver = new AddressReceiver(new Handler(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_form);

        dobText=  findViewById(R.id.dateOfBirth);
        userName = findViewById(R.id.userName);
        workAddress = findViewById(R.id.workAdress);
        homeAddress = findViewById(R.id.homeAddress);
        save_profile = findViewById(R.id.SaveProfButton);
        userInfo = getSharedPreferences(Constants.USER_INFO_SHARED_PREFERENCES,MODE_PRIVATE);

        checkedIcon = getApplicationContext().getResources().getDrawable(R.drawable.ic_check_black_24dp);
        homeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // home address click
                Intent homeIntent = new Intent(UserInfoFormActivity.this, AddressPickerMapsActivity.class);
                startActivityForResult(homeIntent, HOME_ADDRESS_PICKER);

            }
        });

        workAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // work address click

                Intent workIntent = new Intent(UserInfoFormActivity.this, AddressPickerMapsActivity.class);
                startActivityForResult(workIntent, WORK_ADDRESS_PICKER);

            }
        });

        save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(homeLatLng.equals("") || homeAddressVariable.equals("") || RequiredEditText(userName) || RequiredEditText(dobText))
                {
                    if(homeLatLng.equals(""))
                        homeAddress.setError("Required");
                    else if(homeAddressVariable.equals("")) {
                        Toast.makeText(UserInfoFormActivity.this,
                                "please wait as we fetch your address",
                                Toast.LENGTH_LONG)
                                .show();
                    }

                    return;
                }

                final String name,day,month,year,dateOfBirth,contactNumber;
                name=userName.getText().toString();
                dateOfBirth=dobText.getText().toString();

                contactNumber=userInfo.getString(Constants.user_phone_no_preference,"Not Defined");

                if(workLatLng.equals("")){
                    userInfoData=new UserInfoData(name,dateOfBirth,homeLatLng,contactNumber, homeAddressVariable);

                }
                else {
                    userInfoData = new UserInfoData(name, dateOfBirth, workLatLng, homeLatLng, contactNumber, homeAddressVariable, workAddressVariable);
                    userInfo.edit().putString(Constants.user_work_address_latlng_preference,workLatLng).apply();
                    userInfo.edit().putString(Constants.user_work_address_preference,workAddressVariable).apply();
                }
                //applying values to the info names Shared Preference

                userInfo.edit().putString(Constants.username_preference,name).apply();
                userInfo.edit().putString(Constants.user_dob_preference,dateOfBirth).apply();
                userInfo.edit().putString(Constants.user_home_address_latlng_preference,homeLatLng).apply();
                userInfo.edit().putString(Constants.uid_preference,uid).apply();
                //userInfo.edit().putString(Constants.user_phone_no_preference,PHONE_NUMBER).apply();
                userInfo.edit().putBoolean(Constants.user_exists_preference,true).apply();

                // set the home address fetched using intent service
                userInfo.edit().putString(Constants.user_home_address_preference, homeAddressVariable).apply();

                database = FirebaseDatabase.getInstance();
                userInfoRef = database.getReference(path).child(uid);
                userInfoRef.setValue(userInfoData);


                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                finish();

            }
        });
    }



    //Home Address field's onCLick function
    public void setHomeAddress(View v){

    }
    //Work ADDress field's onlick funcion
    public void setWorkAddress(View v){

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*

        receive latLong picked from map

         */

        switch (requestCode){

            case (HOME_ADDRESS_PICKER):
                if(resultCode == RESULT_OK){

                    // set the home LatLng
                    homeLatLng = data.getStringExtra("latitude-longitude");
                    Log.d(LogTags.Map_TAG, "onActivityResult: home latLng fetched = "+homeLatLng);

                    // start address fetch intent service
                    String[] latLng = homeLatLng.split(",");
                    addressReceiver.startAddressFetchService(
                            this,
                            Double.valueOf(latLng[0]),
                            Double.valueOf(latLng[1]),
                            0
                    );

                    //onSuccess
                    homeAddress.setText(getText(R.string.address_fetching_text));
                    homeAddress.setCompoundDrawables(null,null,checkedIcon,null);

                }

                break;

            case (WORK_ADDRESS_PICKER):
                if(resultCode == RESULT_OK){

                    // set the work address
                    workLatLng = data.getStringExtra("latitude-longitude");
                    Log.d(LogTags.Map_TAG, "onActivityResult: work latLng fetched = "+workLatLng);

                    // start address fetch intent service
                    String[] latLng = workLatLng.split(",");
                    addressReceiver.startAddressFetchService(
                            this,
                            Double.valueOf(latLng[0]),
                            Double.valueOf(latLng[1]),
                            1
                    );

                    //onSuccess
                    workAddress.setCompoundDrawables(null,null,checkedIcon,null);
                    workAddress.setText(getText(R.string.address_fetching_text));

                }

                break;
        }

    }


    private boolean RequiredEditText(EditText e)
    {
        if(e.getText().toString().length()==0)
        {
            e.setError("Required");
            return true;
        }

        return false;
    }


    @Override
    public void updateAddress(String address, int type) {
        /*
        address received callback
         */

        if(type == 0) {
            // home address

            homeAddressVariable = address;
            homeAddress.setText(homeAddressVariable);
        }

        else if(type==1){
            // work address

            workAddressVariable = address;
            workAddress.setText(workAddressVariable);

        }

    }
}
