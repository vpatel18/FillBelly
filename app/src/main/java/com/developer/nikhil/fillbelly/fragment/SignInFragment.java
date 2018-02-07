package com.developer.nikhil.fillbelly.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.activity.HomeActivity;
import com.developer.nikhil.fillbelly.common.ApplicationHelper;
import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.developer.nikhil.fillbelly.activity.BaseActivity.hideProgressDialog;
import static com.developer.nikhil.fillbelly.common.Utils.ShowProgressDialog;


public class SignInFragment extends Fragment implements View.OnClickListener {

    private Button btnSignIn, btnSubmit, btnRegister;
    private EditText edtUserName, edtPassword, edtEmail, edtOtpEmail;
    private TextView tvForgotPassword;
    private Dialog pDialog, pDialogOtp;
    private ImageButton ibtnClose;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_signin, container, false);

        tvForgotPassword = (TextView) view.findViewById(R.id.tvForgotPassword);
        btnSignIn = (Button) view.findViewById(R.id.btnSignIn);
        edtUserName = (EditText) view.findViewById(R.id.edtUserName);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
//        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Raleway_Medium.ttf");

        tvForgotPassword.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tvForgotPassword:
                DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
                int screenWidth = (int) (metrics.widthPixels * 0.90);
                pDialog = new Dialog(getActivity());
                pDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pDialog.setContentView(R.layout.dialog_forgot_password);
                pDialog.getWindow().setLayout(screenWidth, RecyclerView.LayoutParams.WRAP_CONTENT);
                pDialog.show();
                btnSubmit = (Button) pDialog.findViewById(R.id.btnSubmit);
                edtEmail = (EditText) pDialog.findViewById(R.id.edtEmail);
                ibtnClose = (ImageButton) pDialog.findViewById(R.id.ibtnClose);

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.isEmpty(edtEmail.getText().toString().trim()) || edtEmail.length() <= 0)
                            Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_email));
                        else if (!Utils.isValidEmail(edtEmail.getText().toString().trim()))
                            Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_valid_email));
                        else {
                            if (Utils.isNetworkAvailable(getActivity(), true, false)) {
                                forgotPassword();
//                                pDialog.dismiss();
                            } else {
                                Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.network_alert));
                            }
                        }
                    }
                });

                ibtnClose.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        pDialog.cancel();
                    }
                });

                break;

            case R.id.btnSignIn:
                if (Utils.isEmpty(edtUserName.getText().toString().trim()) || edtUserName.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_email));
                else if (!Utils.isValidEmail(edtUserName.getText().toString().trim()))
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_valid_email));
                else if (Utils.isEmpty(edtPassword.getText().toString().trim()) || edtPassword.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_password));
                else {
//                    intent = new Intent(getActivity(), HomeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
                    if (Utils.isNetworkAvailable(getActivity(), true, false)) {
                        login();
                    } else {
                        Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.network_alert));
                    }
                }

                break;

        }

    }


    private void forgotPassword() {
        ShowProgressDialog(getActivity(), getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.GET,
                (Constants.BASE_URL + Constants.FORGOT_PASSWORD + "Email=" + edtEmail.getText().toString()),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("!_@@ Forgot Password Resp:>", "" + response);
                        // Hide ProgressDialog
                        hideProgressDialog();
                        try {
                            String ResponseCode = "", ResponseMsg = "", error = "";

                            JSONObject object = new JSONObject(response);
                            if (object != null) {
                                ResponseMsg = object.getString("ErrorMessage");
                                if (error.equals("Success")) {
                                    Utils.showAlert(getActivity(), getString(R.string.app_name), "Please check your mail to reset your password.");
                                    pDialog.dismiss();
                                    hideProgressDialog();
                                } else {
                                    Utils.showAlert(getActivity(), getString(R.string.app_name), ResponseMsg);
                                    pDialog.dismiss();
                                    hideProgressDialog();
                                }
                                hideProgressDialog();
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
                        Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_network));
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put(Constants.TXTUSERNAME, edtEmail.getText().toString().trim());
                Log.e("!_@@ Parms :", "" + params);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        //set Time Out to 60 sec
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }


    private void login() {
        ShowProgressDialog(getActivity(), getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                (Constants.BASE_URL + Constants.LOGIN),
                new Response.Listener<String>() {
                    String error_msg;

                    @Override
                    public void onResponse(String response) {
                        Log.e("!_@@ Login Resp:>", "" + response);
                        // Hide ProgressDialog
                        Utils.hideProgressDialog();
                        try {
                            String ResponseCode = "", ResponseMsg = "", error = "";
                            JSONObject jobj = new JSONObject(response);
                            Log.e("SignInFragment", "response::>>> " + response);
                            ResponseMsg = jobj.getString("ErrorMessage");
                            if (ResponseMsg.equals("Failed")) {
                                ApplicationHelper.showToast(getActivity(), ResponseMsg);
                            } else {
                                JSONObject innerjobj = jobj.getJSONObject("User");
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

                                    ApplicationHelper.showToast(getActivity(), ResponseMsg);

                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
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
                        Utils.hideProgressDialog();
                        Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_network));
                    }
                }) {
            /*@Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }*/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Email", edtUserName.getText().toString());
                params.put("Password", edtPassword.getText().toString());
                Log.e("!_@@ Parms :", "" + params);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        //set Time Out to 60 sec
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

}
