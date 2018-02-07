package com.developer.nikhil.fillbelly.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class SignUpFragment extends Fragment {

    private EditText edtFname, edtLname, edtEmail, edtPassword, edtConfirmPassword, edtPhone;
    private Button btnSignUp;
    private TextView tvTC, tvPolicy;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_signup, container, false);

        edtFname = (EditText) view.findViewById(R.id.edtFname);
        edtLname = (EditText) view.findViewById(R.id.edtLname);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText) view.findViewById(R.id.edtConfirmPassword);
        edtPhone = (EditText) view.findViewById(R.id.edtPhone);
        tvTC = (TextView) view.findViewById(R.id.tvTC);
        tvPolicy = (TextView) view.findViewById(R.id.tvPolicy);

        SpannableString content = new SpannableString("T&C");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvTC.setText(content);

        SpannableString content1 = new SpannableString("Privacy Policy");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        tvPolicy.setText(content1);

        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isEmpty(edtFname.getText().toString().trim()) || edtFname.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_first_name));
                else if (Utils.isEmpty(edtLname.getText().toString().trim()) || edtLname.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_last_name));
                else if (Utils.isEmpty(edtEmail.getText().toString().trim()) || edtEmail.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_email));
                else if (!Utils.isValidEmail(edtEmail.getText().toString().trim()))
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_valid_email));
                else if (Utils.isEmpty(edtPassword.getText().toString().trim()) || edtPassword.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_password));
                else if (edtPassword.length() < 5)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_password_length));
                else if (Utils.isEmpty(edtConfirmPassword.getText().toString().trim()) || edtConfirmPassword.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_confirm_pass));
                else if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString()) == false)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_pass_not_match));
                else if (Utils.isEmpty(edtPhone.getText().toString().trim()) || edtPhone.length() <= 0)
                    Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.alert_phone_number));
                else {
                    if (Utils.isNetworkAvailable(getActivity(), true, false)) {
                        register();
                    } else {
                        Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.network_alert));
                    }
                }

            }
        });

        tvPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Terms_Conditions.class);
//                intent.putExtra("from", "6");
//                Log.e("SignUp","::>> tvPolicy....");
//                getActivity().startActivity(intent);
            }
        });

        tvTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Terms_Conditions.class);
//                intent.putExtra("from", "4");
//                getActivity().startActivity(intent);
            }
        });

        return view;
    }


    private void register() {
        ShowProgressDialog(getActivity(), getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                (Constants.BASE_URL + Constants.REGISTER),
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
                            Log.e("SignUpFragment", "response::>>> " + response);
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

                                    ApplicationHelper.showToast(getActivity(), ResponseMsg);
                                }
                            }

                            edtFname.setText(null);
                            edtLname.setText(null);
                            edtEmail.setText(null);
                            edtPhone.setText(null);
                            edtPassword.setText(null);
                            edtConfirmPassword.setText(null);

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
                params.put("FirstName", edtFname.getText().toString().trim());
                params.put("LastName", edtLname.getText().toString().trim());
                params.put("Email", edtEmail.getText().toString().trim());
                params.put("Password", edtPassword.getText().toString().trim());
                params.put("Phone", edtPhone.getText().toString().trim());
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
