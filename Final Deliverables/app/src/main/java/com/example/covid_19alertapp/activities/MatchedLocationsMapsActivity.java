package com.example.covid_19alertapp.activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.covid_19alertapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MatchedLocationsMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double blLatitude, blLongitude;

    // intent value keys
    private static final String BL_LATITUDE_KEY = "maps-blLatitude";
    private static final String BL_LONGITUDE_KEY = "maps-blLongitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fetch latitude, longitude
        fetchLatLng();

        setContentView(R.layout.activity_location_show_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void fetchLatLng() {

        this.blLatitude = getIntent().getDoubleExtra(BL_LATITUDE_KEY, 23.8103);
        this.blLongitude = getIntent().getDoubleExtra(BL_LONGITUDE_KEY, 90.4125);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // show polygon
        PolygonOptions polygonOptions = new PolygonOptions()
                .add(
                        new LatLng(blLatitude, blLongitude),
                        new LatLng(blLatitude+0.0002, blLongitude),
                        new LatLng(blLatitude+0.0002, blLongitude+0.0002),
                        new LatLng(blLatitude, blLongitude+0.0002),
                        new LatLng(blLatitude, blLongitude)

                )
                .fillColor(Color.argb(20, 255, 0, 0))
                .strokeColor(Color.rgb(255,0,0));

        Polygon polygon = mMap.addPolygon(polygonOptions);

        /* Add a marker in Infected Position and move the camera
        LatLng infectedPosition = new LatLng(this.latitude, this.longitude);
        mMap.addMarker(new MarkerOptions().position(infectedPosition).title("infected point"));*/

        LatLng middlePoint
                = new LatLng(this.blLatitude+0.0002, this.blLongitude+0.0002);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(middlePoint, 19.0f));
    }

    public static String getBlLatitudeKey() {
        return BL_LATITUDE_KEY;
    }

    public static String getBlLongitudeKey() {
        return BL_LONGITUDE_KEY;
    }
}
