<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:map="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".activities.AddressPickerMapsActivity">

    <fragment
        android:id="@+id/autocomplete_fragment"
        android:name="com.google.android.libraries.places.widget.AutocompleteSupportFragment"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />


    <fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="2" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="12"
        android:orientation="horizontal">

        <TextView
            android:id="@+id/userHelperText"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="5"
            android:text="@string/map_text"
            android:textSize="14sp" />

        <Button
            android:id="@+id/confirm_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:enabled="false"
            android:onClick="confirmClicked"
            android:text="@string/map_confirm_button_text"
            android:textSize="12sp" />
    </LinearLayout>


</LinearLayout>
