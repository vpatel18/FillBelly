package com.developer.nikhil.fillbelly.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.developer.nikhil.fillbelly.activity.BaseActivity.hideProgressDialog;
import static com.developer.nikhil.fillbelly.common.Utils.ShowProgressDialog;


public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;
    private static Activity activity;
    final int PERMISSION_REQUEST_CODE = 111;
    static String FLAG_FIRST_TIME = "first_time";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = SplashActivity.this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!checkSystemAlertWindowPermission() || !checkAccessFineLocation() ||
                            !checkCoarseLocation()) {
                        requestPermission();

                    } else {
                        IntentActivity();
                    }
                } else {
                    IntentActivity();
                }
            }
        }, SPLASH_TIME_OUT);

    }


    private void IntentActivity() {
        // This method will be executed once the timer is over
        // Start your app main activity
        Intent intent;
        if (Preferences.getUserId() != null) {
            intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent(activity, Login_SignupActivity.class);
            startActivity(intent);
            finish();
        }

    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                PERMISSION_REQUEST_CODE);
    }


    private boolean checkCameraPermission() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkWriteExternalStorage() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkReadExternalStorage() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkSystemAlertWindowPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkAccessFineLocation() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    private boolean checkCoarseLocation() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IntentActivity();
                } else {
                    finish();
                }
                break;
        }
    }
}
