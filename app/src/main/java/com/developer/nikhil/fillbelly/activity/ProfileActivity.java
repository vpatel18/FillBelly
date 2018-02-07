package com.developer.nikhil.fillbelly.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.common.ApplicationHelper;
import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfileActivity extends BaseActivity {

    private static final String TAG = "ProfileActivity.class";
    private static Activity activity;
    private EditText edtFname, edtLname, edtEmail, edtPhone, edtPassword, edtNewPassword, edtConfirmPassword;
    private Button btnSave;
    private TextView tvProfile;
    private ImageView ivProfile, ivBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        activity = ProfileActivity.this;
        findviews();

    }

    private void findviews() {
        edtFname = (EditText) findViewById(R.id.edtFname);
        edtLname = (EditText) findViewById(R.id.edtLname);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSave = (Button) findViewById(R.id.btnSave);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);

        if (Preferences.getFname() != null) {
            edtFname.setText(Preferences.getFname());
            edtFname.setTextColor(getResources().getColor(R.color.dark_blue_with_87));
            Log.e("Profile", "::>> " + Preferences.getFname());
        }
        if (Preferences.getLname() != null) {
            edtLname.setText(Preferences.getLname());
            edtLname.setTextColor(getResources().getColor(R.color.dark_blue_with_87));
            Log.e("Profile", "::>> " + Preferences.getLname());
        }
        if (Preferences.getUseremail() != null) {
            edtEmail.setText(Preferences.getUseremail());
            edtEmail.setTextColor(getResources().getColor(R.color.dark_blue_with_87));
            Log.e("Profile", "::>> " + Preferences.getUseremail());
        }
        if (Preferences.getPhone() != null) {
            edtPhone.setText(Preferences.getPhone());
            edtPhone.setTextColor(getResources().getColor(R.color.dark_blue_with_87));
            Log.e("Profile", "::>> " + Preferences.getPhone());
        }

        edtFname.setEnabled(false);
        edtLname.setEnabled(false);
        edtPhone.setEnabled(false);
        edtEmail.setEnabled(false);
        btnSave.setEnabled(false);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setEnabled...
                edtFname.setEnabled(true);
                edtLname.setEnabled(true);
                edtPhone.setEnabled(true);
//                edtEmail.setEnabled(true);
                btnSave.setEnabled(true);

                edtFname.setTextColor(getResources().getColor(R.color.colorPrimary));
                edtLname.setTextColor(getResources().getColor(R.color.colorPrimary));
//                edtEmail.setTextColor(getResources().getColor(R.color.colorPrimary));
                edtPhone.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(activity, true, false)) {
                    editProfile();
                } else {
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.network_alert));
                }
            }
        });
    }


    private void editProfile() {
        ShowProgressDialog(activity, getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                (Constants.BASE_URL + Constants.EDITPROFILE),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("!_@@ Update Profile Resp:>", "" + response);
                        // Hide ProgressDialog
                        hideProgressDialog();
                        try {
                            String ResponseCode = "", ResponseMsg = "", error = "";

                            JSONObject object = new JSONObject(response);
                            if (object != null) {
                                ResponseMsg = object.getString("ErrorMessage");
                                Log.e("Update Profile", ":::>>> " + ResponseMsg);
                                if (ResponseMsg.equals("Success")) {
                                    JSONObject innerjobj = object.getJSONObject("User");
                                    if (innerjobj != null) {
                                        String ID = innerjobj.getString("ID");
                                        String FirstName = innerjobj.getString("FirstName");
                                        String LastName = innerjobj.getString("LastName");
                                        String Email = innerjobj.getString("Email");
                                        String PhoneNumber = innerjobj.getString("PhoneNumber");

                                        Preferences.setUserId(ID);
                                        Preferences.setUseremail(Email);
                                        Preferences.setFname(FirstName);
                                        Preferences.setLname(LastName);
                                        Preferences.setPhone(PhoneNumber);
                                        Log.e(TAG, "::>> " + Preferences.getUserId() + "::" +
                                        Preferences.getFname() + "::" + Preferences.getLname()+
                                        "::" + Preferences.getPhone());
                                        ApplicationHelper.showToast(activity, "Your Profile has been updated.");
                                        finish();
                                    } else {
                                        Utils.showAlert(activity, getString(R.string.app_name), ResponseMsg);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error..", ": " + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Errorlist", String.valueOf(error));
                        // Hide ProgressDialog
                        hideProgressDialog();
                        Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_network));
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", Preferences.getUserId());
                params.put("FirstName", edtFname.getText().toString().trim());
                params.put("LastName", edtLname.getText().toString().trim());
                params.put("Phone", edtPhone.getText().toString().trim());
                Log.e("!_@@ Parms :", "" + params);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(activity);
        //set Time Out to 60 sec
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

}
