package com.developer.nikhil.fillbelly.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.common.GPSTracker;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

public class SearchActivity extends BaseActivity {

    private ImageView ivClose;
    private TextView tvTitle;
    private EditText edtSearch;
    private TextView tvLocation, tvAddress;
    private static Activity activity;
    private LocationManager locationManager;
    private GPSTracker gps;
    private double latitude, longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        activity = SearchActivity.this;

        findviews();
        // create class object
        gps = new GPSTracker(getApplication());
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Log.e("HomeActivity", "::>> " + latitude + " ::: " + longitude);
            Preferences.setLatitude(String.valueOf(latitude));
            Preferences.setLongitude(String.valueOf(longitude));

        } else {
            Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_gps_further));
        }
    }

    private void findviews() {
        ivClose = (ImageView) findViewById(R.id.ivClose);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        tvAddress = (TextView) findViewById(R.id.tvAddress);

        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setLatitude(String.valueOf(latitude));
                Preferences.setLongitude(String.valueOf(longitude));
                Log.e("SearchActivity", "::>>> " + latitude + "::" + longitude);
                finish();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPlace(view);
            }
        });
    }


    public void findPlace(View view) {
        try {
            Intent intent = new PlaceAutocomplete
                    .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    // A place has been received; use requestCode to track the request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                try {
                    // retrive the data by using getPlace() method.
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber() + place.getLatLng());
                    String s = place.getLatLng().toString();
                    String[] latLng = s.substring(10, s.length() - 1).split(",");
                    String sLat = latLng[0];
                    String sLng = latLng[1];

                    String mystring = place.getAddress().toString();
                    String arr[] = mystring.split(" ", 2);
                    String firstWord = arr[0];   //the
                    String theRest = arr[1];
                    Preferences.setAddress(firstWord);

                    Log.e("SearchActivity", "::>> " + sLat + "::" + sLng + "::>> " + firstWord);
                    Preferences.setLatitude(sLat);
                    Preferences.setLongitude(sLng);
                    edtSearch.setText(place.getAddress());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 500);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage() + status);

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
