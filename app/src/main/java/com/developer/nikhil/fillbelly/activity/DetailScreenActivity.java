package com.developer.nikhil.fillbelly.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.adapter.DeliveryAdapter;
import com.developer.nikhil.fillbelly.common.ApplicationHelper;
import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;
import com.developer.nikhil.fillbelly.model.HotelBean;
import com.developer.nikhil.fillbelly.model.NearByBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DetailScreenActivity extends BaseActivity implements View.OnClickListener {

    private static Activity activity;
    //    private ViewPager viewpager;
    private ImageView ivBack, ivCollection;
    private TextView tvHotelName, tvRating, tvLocation, tvCuisine, tvCostFor2, tvPriceRange, tvDelivery, tvVotes;
    private Button btnFeedback, btnRateSubmit, btnGraph;
    private Dialog pDialog;
    private RatingBar rating;
    private EditText edtComment;
    private ImageButton ibtnClose;
    private String hotelname, cuisines, ratings, average_cost_for_two, has_online_delivery, price_range, votes, address, id,
            ratevalue, image_url;
    private NearByBean bean;
    private ArrayList<NearByBean> graphlist = new ArrayList<NearByBean>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        activity = DetailScreenActivity.this;
        findviews();

        Intent intent = getIntent();
        hotelname = intent.getStringExtra("hotelname");
        cuisines = intent.getStringExtra("cuisines");
        ratings = intent.getStringExtra("ratings");
        average_cost_for_two = intent.getStringExtra("average_cost_for_two");
        has_online_delivery = intent.getStringExtra("has_online_delivery");
        price_range = intent.getStringExtra("price_range");
        votes = intent.getStringExtra("votes");
        address = intent.getStringExtra("address");
        id = intent.getStringExtra("id");
        image_url = intent.getStringExtra("image_url");
        Log.e("DetailScreen","::>>id " + id);

        tvHotelName.setText(hotelname);
        tvCuisine.setText(cuisines);
        tvRating.setText(ratings + " /5");
        tvCostFor2.setText(average_cost_for_two + " $");

        if (has_online_delivery.equals("1")) {
            tvDelivery.setText("Yes");
        } else {
            tvDelivery.setText("No");
        }
        if (price_range.equals("3"))
            tvPriceRange.setText("$$$");
        else if (price_range.equals("2"))
            tvPriceRange.setText("$$");
        else
            tvPriceRange.setText("$");
        tvVotes.setText(votes);
        tvLocation.setText(address);

        Glide.with(activity).load(image_url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_headerr)
                .into(ivCollection);

        if (Preferences.getFreqhotelname() != null) {
            if (Preferences.getFreqhotelname().equals(hotelname)) {
                showDialog();
            } else {
                Preferences.setFreqhotelname(hotelname);
            }
        } else {
            Preferences.setFreqhotelname(hotelname);
        }

        getRatingList();
    }


    private void findviews() {
//        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivCollection = (ImageView) findViewById(R.id.ivCollection);
        tvRating = (TextView) findViewById(R.id.tvRating);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvCuisine = (TextView) findViewById(R.id.tvCuisine);
        tvHotelName = (TextView) findViewById(R.id.tvHotelName);
        tvCostFor2 = (TextView) findViewById(R.id.tvCostFor2);
        tvPriceRange = (TextView) findViewById(R.id.tvPriceRange);
        tvDelivery = (TextView) findViewById(R.id.tvDelivery);
        tvVotes = (TextView) findViewById(R.id.tvVotes);

        btnFeedback = (Button) findViewById(R.id.btnFeedback);
        btnGraph = (Button) findViewById(R.id.btnGraph);

        btnFeedback.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btnGraph.setOnClickListener(this);

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Did you actually visited this Hotel?");
        String positiveText = getString(android.R.string.yes);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ratethis();
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.no);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                try {
                    graphlist.clear();
                    Preferences.setGraphlist(graphlist);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
                break;

            case R.id.btnFeedback:
                ratethis();
                break;

            case R.id.btnGraph:
                Intent intent = new Intent(activity, GraphActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void ratethis() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.90);
        pDialog = new Dialog(activity);
        pDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pDialog.setContentView(R.layout.dialog_feedback);
        pDialog.getWindow().setLayout(screenWidth, RecyclerView.LayoutParams.WRAP_CONTENT);
        pDialog.show();
        btnRateSubmit = (Button) pDialog.findViewById(R.id.btnRateSubmit);
        edtComment = (EditText) pDialog.findViewById(R.id.edtComment);
        rating = (RatingBar) pDialog.findViewById(R.id.rating);
        ibtnClose = (ImageButton) pDialog.findViewById(R.id.ibtnClose);

        btnRateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isEmpty(edtComment.getText().toString().trim()) || edtComment.length() <= 0)
                    Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_comment));
                else {
                    if (Utils.isNetworkAvailable(activity, true, false)) {
                        ratingUs();
                    } else {
                        Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.network_alert));
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

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar arg0, float rateValue, boolean arg2) {
                // TODO Auto-generated method stub
                Log.e("Rating", "your selected value is :" + rateValue);
                ratevalue = String.valueOf(rateValue);
            }
        });

    }

    private void ratingUs() {
        BaseActivity.ShowProgressDialog(activity, getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                (Constants.BASE_URL + Constants.FEEDBACK),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("!_@@ getHotelList Resp:>", "" + response);
                        // Hide ProgressDialog
                        hideProgressDialog();
                        try {
                            String ResponseCode = "", ResponseMsg = "";
                            boolean error;
                            JSONObject object = new JSONObject(response);
                            ResponseMsg = object.getString("ErrorMessage");
                            if (ResponseMsg.equals("Success")) {
                                ApplicationHelper.showToast(activity, "Thank you for Feedback");
                                pDialog.dismiss();

                            } else {
                                ApplicationHelper.showToast(activity, "Thank you for Feedback.");
                                pDialog.dismiss();
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
//                params.put(Constants.TXTUID, Preferences.getUserId());
                params.put("userID", Preferences.getUserId());
                params.put("resID", id);
                params.put("Comments", edtComment.getText().toString());
                params.put("Ratings", ratevalue);
                Log.e("!_@@ Parms :", "" + params);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(activity);
        //set Time Out to 60 sec
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }


    private void getRatingList() {
        Log.e("Detail", "getRatingList" + id);
//        BaseActivity.ShowProgressDialog(activity, getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.GET,
                ("http://www.saddaaddaweb.com/api/profile/GetRatings?RestID=" + id),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("!_@@ getHotelList Resp:>", "" + response);
                        // Hide ProgressDialog
//                        hideProgressDialog();
                        try {
                            String ResponseCode = "", ResponseMsg = "";
                            boolean error;
                            JSONObject object = new JSONObject(response);
                            ResponseMsg = object.getString("ErrorMessage");
                            if (ResponseMsg.equals("Success")) {
                                JSONArray array = object.getJSONArray("Rating");
                                for (int i = 0; i < array.length(); i++) {
                                    bean = new NearByBean();
                                    String rate = array.getString(i);
                                    bean.setY(rate);
                                    graphlist.add(bean);
                                    Preferences.setGraphlist(graphlist);
                                }
                            } else {
                                Log.e("DetailScreen", "::>>> getRatingList");
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
//                        hideProgressDialog();
//                        Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_network));
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put(Constants.TXTUID, Preferences.getUserId());
                Log.e("!_@@ Parms :", "" + params);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(activity);
        //set Time Out to 60 sec
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            graphlist.clear();
            Preferences.setGraphlist(graphlist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("DetailScreen", "::>>> onBackPressed...");
    }

}
