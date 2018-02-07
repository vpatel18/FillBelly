package com.developer.nikhil.fillbelly.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.activity.BaseActivity;
import com.developer.nikhil.fillbelly.adapter.DeliveryAdapter;
import com.developer.nikhil.fillbelly.adapter.NearByAdapter;
import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;
import com.developer.nikhil.fillbelly.model.HotelBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.developer.nikhil.fillbelly.activity.BaseActivity.hideProgressDialog;
import static com.developer.nikhil.fillbelly.common.Utils.ShowProgressDialog;


public class DeliveryFragment extends Fragment {

    private RecyclerView rvNearBy;
    private static final String TAG = "DeliveryFragment.class";
    private ArrayList<HotelBean> hotellist = new ArrayList<HotelBean>();
    private ArrayList<String> list = new ArrayList<String>();
    private HotelBean hotelBean = new HotelBean();
    private String city_name, country_name, title, price, cuisine;
    private DeliveryAdapter deliveryAdapter;
    private TextView tvNoData;
    private EditText edtSearch;


    public DeliveryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_nearby, container, false);
        rvNearBy = (RecyclerView) view.findViewById(R.id.rvNearBy);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        edtSearch = (EditText) view.findViewById(R.id.edtSearch);
        rvNearBy.setHasFixedSize(true);
        rvNearBy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        if (Utils.isNetworkAvailable(getActivity(), true, false)) {
            getHotelList();
        } else {
            Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.network_alert));
        }

        /*edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                deliveryAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });*/

        return view;
    }


    private void getHotelList() {
        BaseActivity.ShowProgressDialog(getActivity(), getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                (Constants.BASE_URL + Constants.DELIVERY),
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
                            if (object != null) {
                                String link = object.getString("link");

                                JSONObject innerObj = object.getJSONObject("location");
                                city_name = innerObj.getString("city_name");
                                country_name = innerObj.getString("country_name");
                                title = innerObj.getString("title");

                                JSONArray jAry = object.getJSONArray("nearby_restaurants");
                                for (int i = 0; i < jAry.length(); i++) {
                                    hotelBean = new HotelBean();
                                    JSONObject jObj = jAry.getJSONObject(i);
                                    JSONObject obj = jObj.getJSONObject("restaurant");
                                    String average_cost_for_two = obj.getString("average_cost_for_two");
                                    String cuisines = obj.getString("cuisines");
                                    String currency = obj.getString("currency");
                                    String featured_image = obj.getString("featured_image");
                                    String has_online_delivery = obj.getString("has_online_delivery");
                                    String has_table_booking = obj.getString("has_table_booking");
                                    String id = obj.getString("id");
                                    String is_delivering_now = obj.getString("is_delivering_now");

                                    hotelBean.setAverage_cost_for_two(average_cost_for_two);
                                    hotelBean.setCuisines(cuisines);
                                    hotelBean.setCurrency(currency);
                                    hotelBean.setFeatured_image(featured_image);
                                    hotelBean.setHas_online_delivery(has_online_delivery);
                                    hotelBean.setHas_table_booking(has_table_booking);
                                    hotelBean.setId(id);
                                    hotelBean.setIs_delivering_now(is_delivering_now);

                                    JSONObject locJobj = obj.getJSONObject("location");
                                    String address = locJobj.getString("address");
                                    String city = locJobj.getString("city");
                                    String locality = locJobj.getString("locality");
                                    String locality_verbose = locJobj.getString("locality_verbose");

                                    hotelBean.setAddress(address);
                                    hotelBean.setCity_name(city);
                                    hotelBean.setLocality(locality);
                                    hotelBean.setLocality_verbose(locality_verbose);

                                    String menu_url = obj.getString("menu_url");
                                    String name = obj.getString("name");
                                    String photos_url = obj.getString("photos_url");
                                    String price_range = obj.getString("price_range");
                                    String thumb = obj.getString("thumb");

                                    hotelBean.setMenu_url(menu_url);
                                    hotelBean.setName(name);
                                    hotelBean.setPhotos_url(photos_url);
                                    hotelBean.setPrice_range(price_range);
                                    hotelBean.setThumb(thumb);

                                    JSONObject rateObj = obj.getJSONObject("user_rating");
                                    String aggregate_rating = rateObj.getString("aggregate_rating");
                                    String rating_text = rateObj.getString("rating_text");
                                    String votes = rateObj.getString("votes");

                                    hotelBean.setUser_rating(aggregate_rating);
                                    hotelBean.setRating_text(rating_text);
                                    hotelBean.setVotes(votes);

                                    hotelBean.setCity_name(city_name);
                                    hotelBean.setCountry_name(country_name);
                                    hotelBean.setTitle(title);

                                    hotellist.add(hotelBean);
                                }

                            }

                            if (hotellist.size() == 0) {
                                rvNearBy.setVisibility(View.GONE);
                                tvNoData.setVisibility(View.VISIBLE);
                            } else {
                                rvNearBy.setVisibility(View.VISIBLE);
                                tvNoData.setVisibility(View.GONE);
                            }
                            deliveryAdapter = new DeliveryAdapter(getActivity(), hotellist);
                            rvNearBy.setAdapter(deliveryAdapter);
                            deliveryAdapter.notifyDataSetChanged();

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
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Lat", Preferences.getLatitude());
                params.put("Long", Preferences.getLongitude());
                if (Preferences.getFilterRate() != null) {
                    params.put("Rating", Preferences.getFilterRate());
                }
                if (Preferences.getFilterPrice1() != null)
                    price = "1";
                if (Preferences.getFilterPrice2() != null)
                    price = "2";
                if (Preferences.getFilterPrice3() != null)
                    price = "3";
                if (Preferences.getFilterPrice1() != null && Preferences.getFilterPrice2() != null)
                    price = "1" + "," + "2";
                if (Preferences.getFilterPrice2() != null && Preferences.getFilterPrice3() != null)
                    price = "2" + "," + "3";
                if (Preferences.getFilterPrice1() != null && Preferences.getFilterPrice3() != null)
                    price = "1" + "," + "3";
                if (Preferences.getFilterPrice1() != null && Preferences.getFilterPrice2() != null && Preferences.getFilterPrice3() != null)
                    price = "1" + "," + "2" + "," + "3";

                JSONArray list = new JSONArray();
                if (Preferences.getC1() != null)
                    list.put("Indian");
                if (Preferences.getC2() != null)
                    list.put("Italian");
                if (Preferences.getC3() != null)
                    list.put("French");
                if (Preferences.getC4() != null)
                    list.put("Japanese");
                if (Preferences.getC5() != null)
                    list.put("Mediterranean");
                if (Preferences.getC6() != null)
                    list.put("Mexican");
                if (Preferences.getC7() != null)
                    list.put("Morrocon");
                if (Preferences.getC8() != null)
                    list.put("Thai");
                if (Preferences.getC9() != null)
                    list.put("Chinese");
                if (Preferences.getC10() != null)
                    list.put("American");
                if (Preferences.getC11() != null)
                    list.put("Hawaiian");
                if (Preferences.getC12() != null)
                    list.put("Brazilian");
                if (Preferences.getC13() != null)
                    list.put("Cajun Food");

                Log.e(TAG, "::>> " + list);

                if (list.length() != 0) {
                    Log.e(TAG, "::>>" + list);
                    params.put("Cuisines", String.valueOf(list));
                }

                if (price != null)
                    params.put("price_range", price);

                Log.e("!_@@ Parms :", "" + params);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        //set Time Out to 60 sec
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

    private void getCuisineParam() {
        if (Preferences.getC1() != null)
            cuisine = Preferences.getC1();
        if (Preferences.getC2() != null)
            cuisine = Preferences.getC2();
        if (Preferences.getC3() != null)
            cuisine = Preferences.getC3();
        if (Preferences.getC4() != null)
            cuisine = Preferences.getC4();
        if (Preferences.getC5() != null)
            cuisine = Preferences.getC5();
        if (Preferences.getC6() != null)
            cuisine = Preferences.getC6();
        if (Preferences.getC7() != null)
            cuisine = Preferences.getC7();
        if (Preferences.getC8() != null)
            cuisine = Preferences.getC8();
        if (Preferences.getC9() != null)
            cuisine = Preferences.getC9();
        if (Preferences.getC10() != null)
            cuisine = Preferences.getC10();
        if (Preferences.getC11() != null)
            cuisine = Preferences.getC11();
        if (Preferences.getC12() != null)
            cuisine = Preferences.getC12();
        if (Preferences.getC13() != null)
            cuisine = Preferences.getC13();
    }

}
