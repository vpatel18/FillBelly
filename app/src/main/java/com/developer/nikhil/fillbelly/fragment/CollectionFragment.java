package com.developer.nikhil.fillbelly.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.developer.nikhil.fillbelly.adapter.CollectionAdapter;
import com.developer.nikhil.fillbelly.adapter.DeliveryAdapter;
import com.developer.nikhil.fillbelly.adapter.NearByAdapter;
import com.developer.nikhil.fillbelly.common.ApplicationHelper;
import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;
import com.developer.nikhil.fillbelly.model.CollectionBean;
import com.developer.nikhil.fillbelly.model.HotelBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.developer.nikhil.fillbelly.activity.BaseActivity.hideProgressDialog;
import static com.developer.nikhil.fillbelly.common.Utils.ShowProgressDialog;


public class CollectionFragment extends Fragment {

    private RecyclerView rvNearBy;
    private static final String TAG = "CollectionFragment.class";
    private ArrayList<CollectionBean> hotellist = new ArrayList<CollectionBean>();
    private CollectionBean collectionBean = new CollectionBean();
    private String city_name, country_name, title, price;
    private CollectionAdapter collectionAdapter;
    private TextView tvNoData;

    public CollectionFragment() {
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
        rvNearBy.setHasFixedSize(true);
        rvNearBy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        if (Utils.isNetworkAvailable(getActivity(), true, false)) {
            getHotelList();
        } else {
            Utils.showAlert(getActivity(), getString(R.string.app_name), getString(R.string.network_alert));
        }

        /*for (int i = 0; i < 10; i++) {
            HotelBean bean = new HotelBean();
            if (i/2 == 0) {
                bean.setTvHotelName("The Fern");
                bean.setTvDetail("Italian, Indian, Thai, Mexican Dishes");
                bean.setTvRating("4.2/5");
            } else {
                bean.setTvHotelName("Prime Divine");
                bean.setTvDetail("Italian, Mexican Dishes");
                bean.setTvRating("3.7/5");
            }
            hotellist.add(bean);
        }

        NearByAdapter adapter = new NearByAdapter(getActivity(), hotellist);
        rvNearBy.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

        return view;
    }


    private void getHotelList() {
        BaseActivity.ShowProgressDialog(getActivity(), getString(R.string.please_wait));
        StringRequest postRequest = new StringRequest(Request.Method.POST,
                (Constants.BASE_URL + Constants.COLLECTION),
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
                                JSONArray jAry = object.getJSONArray("collections");
                                for (int i = 0; i < jAry.length(); i++) {
                                    collectionBean = new CollectionBean();
                                    JSONObject jObj = jAry.getJSONObject(i);
                                    JSONObject innerObj = jObj.getJSONObject("collection");
                                    String collection_id = innerObj.getString("collection_id");
                                    String description = innerObj.getString("description");
                                    String image_url = innerObj.getString("image_url");
                                    String res_count = innerObj.getString("res_count");
                                    String share_url = innerObj.getString("share_url");
                                    String title = innerObj.getString("title");
                                    String url = innerObj.getString("url");

                                    collectionBean.setCollection_id(collection_id);
                                    collectionBean.setDescription(description);
                                    collectionBean.setImage_url(image_url);
                                    collectionBean.setRes_count(res_count);
                                    collectionBean.setShare_url(share_url);
                                    collectionBean.setTitle(title);
                                    collectionBean.setUrl(url);

                                    hotellist.add(collectionBean);
                                }

                                if (hotellist.size() == 0) {
                                    rvNearBy.setVisibility(View.GONE);
                                    tvNoData.setVisibility(View.VISIBLE);
                                } else {
                                    rvNearBy.setVisibility(View.VISIBLE);
                                    tvNoData.setVisibility(View.GONE);
                                }
                                collectionAdapter = new CollectionAdapter(getActivity(), hotellist);
                                rvNearBy.setAdapter(collectionAdapter);
                                collectionAdapter.notifyDataSetChanged();

                            } else {
                                ApplicationHelper.showToast(getActivity(), "Please try again later.");
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
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put(Constants.TXTUID, Preferences.getUserId());
                params.put("Lat", Preferences.getLatitude());
                params.put("Long", Preferences.getLongitude());
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
