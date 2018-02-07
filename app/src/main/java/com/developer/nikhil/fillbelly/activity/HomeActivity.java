package com.developer.nikhil.fillbelly.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.adapter.CuisineAdapter;
import com.developer.nikhil.fillbelly.adapter.ExpandableListAdapter;
import com.developer.nikhil.fillbelly.common.ApplicationHelper;
import com.developer.nikhil.fillbelly.common.GPSTracker;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.common.Utils;
import com.developer.nikhil.fillbelly.fragment.CollectionFragment;
import com.developer.nikhil.fillbelly.fragment.DeliveryFragment;
import com.developer.nikhil.fillbelly.fragment.NearByFragment;
import com.developer.nikhil.fillbelly.fragment.RatingFragment;
import com.developer.nikhil.fillbelly.fragment.TakeOutFragment;
import com.developer.nikhil.fillbelly.model.DrawerBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private ActionBar actionbar;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private LinearLayout frag_container;
    private static Activity activity;
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    private HashMap<String, List<String>> hashMap;
    private ArrayList<String> header;
    private ImageView ivMenu, ivEditProfile, ivSearch;
    private TextView tvFName, tvLName, tvEmail, tvCurLocation, tvCuisine;
    private RelativeLayout rlDrawer;
    private LinearLayout llLogout, llAboutUs, llContactUs;
    private LocationManager locationManager;
    private GPSTracker gps;
    private double latitude, longitude;
    private DrawerBean bean = new DrawerBean();
    private RecyclerView rvCuisine;
    private ArrayList<DrawerBean> cuisinelist = new ArrayList<DrawerBean>();
    private int count = 0, tabselected = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = HomeActivity.this;
        findviews();

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);
        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.delivery)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.near_by)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.rating)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.take_out)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.collection)));

        //replace default fragment
        replaceFragment(new DeliveryFragment());

        //handling tab click event
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new DeliveryFragment());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new NearByFragment());
                    tabselected = 1;
                } else if (tab.getPosition() == 2) {
                    replaceFragment(new RatingFragment());
                    tabselected = 2;
                } else if (tab.getPosition() == 3) {
                    replaceFragment(new TakeOutFragment());
                    tabselected = 3;
                } else {
                    replaceFragment(new CollectionFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        setSupportActionBar(toolbar);
//        initNavigationDrawer();
        setItems();


        adapter = new ExpandableListAdapter(activity, header, hashMap);
        // Setting adpater for expandablelistview
        expandableListView.setAdapter(adapter);

        /*
        You can add listeners for the item clicks
         */
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(), header.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(), header.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(getApplicationContext(), header.get(groupPosition) + " : " + hashMap.get(header.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        // create class object
        gps = new GPSTracker(getApplication());
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Log.e("HomeActivity", "::>> " + latitude + " ::: " + longitude);
            ApplicationHelper.showToast(activity, latitude + "::" + longitude);
            Preferences.setLatitude(String.valueOf(latitude));
            Preferences.setLongitude(String.valueOf(longitude));
            Preferences.setCurLatitude(String.valueOf(latitude));
            Preferences.setCurLongitude(String.valueOf(longitude));
//            tvCurLocation.setText();
            try {
                getAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Utils.showAlert(activity, getString(R.string.app_name), getString(R.string.alert_gps_further));
        }
    }


    // Setting headers and childs to expandable listview
    public void setItems() {

        // Array list for header
        header = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
//        List<String> child3 = new ArrayList<String>();

        // Hash map for both header and child
        hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        /*for (int i = 1; i < 2; i++) {
        }*/

//        header.add("Cuisine");
        header.add("Cost");
        header.add("Rate");


        /*  // Adding child data
        for (int i = 1; i < 5; i++) {
            child1.add("Child" + i);
        }*/

        /*child1.add("Indian");
        child1.add("Italian");
        child1.add("French");
        child1.add("Japanese");
        child1.add("Mediterranean");
        child1.add("Mexican");
        child1.add("Morrocon");
        child1.add("Thai");
        child1.add("Chinese");
        child1.add("American");
        child1.add("Hawaiian");
        child1.add("Brazilian");
        child1.add("Cajun Food");*/

        child1.add("$");
        child1.add("$$");
        child1.add("$$$");

        child2.add("");
        // Adding header and childs to hash map
//        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);


    }

    private void findviews() {
        frag_container = (LinearLayout) findViewById(R.id.frag_container);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        ivEditProfile = (ImageView) findViewById(R.id.ivEditProfile);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        rlDrawer = (RelativeLayout) findViewById(R.id.rlDrawer);
        llLogout = (LinearLayout) findViewById(R.id.llLogout);
        llAboutUs = (LinearLayout) findViewById(R.id.llAboutUs);
        llContactUs = (LinearLayout) findViewById(R.id.llContactUs);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        tvFName = (TextView) findViewById(R.id.tvFName);
        tvLName = (TextView) findViewById(R.id.tvLName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvCurLocation = (TextView) findViewById(R.id.tvCurLocation);
        tvCuisine = (TextView) findViewById(R.id.tvCuisine);
        rvCuisine = (RecyclerView) findViewById(R.id.rvCuisine);
        rvCuisine.setHasFixedSize(true);
        rvCuisine.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));

        tvFName.setText(Preferences.getFname());
        tvLName.setText(Preferences.getLname());
        tvEmail.setText(Preferences.getUseremail());

        Preferences.setFilterRate(null);
        Preferences.setFilterPrice1(null);
        Preferences.setFilterPrice2(null);
        Preferences.setFilterPrice3(null);
        Preferences.setC1(null);
        Preferences.setC2(null);
        Preferences.setC3(null);
        Preferences.setC4(null);
        Preferences.setC5(null);
        Preferences.setC6(null);
        Preferences.setC7(null);
        Preferences.setC8(null);
        Preferences.setC9(null);
        Preferences.setC8(null);
        Preferences.setC10(null);
        Preferences.setC11(null);
        Preferences.setC12(null);
        Preferences.setC13(null);


        for (int i = 0; i < 13; i++) {
            DrawerBean bean = new DrawerBean();
            if (i == 0) {
                bean.setTvCuisine("Indian");
                cuisinelist.add(bean);
            } else if (i == 1) {
                bean.setTvCuisine("Italian");
                cuisinelist.add(bean);
            } else if (i == 2) {
                bean.setTvCuisine("French");
                cuisinelist.add(bean);
            } else if (i == 3) {
                bean.setTvCuisine("Japanese");
                cuisinelist.add(bean);
            } else if (i == 4) {
                bean.setTvCuisine("Mediterranean");
                cuisinelist.add(bean);
            } else if (i == 5) {
                bean.setTvCuisine("Mexican");
                cuisinelist.add(bean);
            } else if (i == 6) {
                bean.setTvCuisine("Morrocon");
                cuisinelist.add(bean);
            } else if (i == 7) {
                bean.setTvCuisine("Thai");
                cuisinelist.add(bean);
            } else if (i == 8) {
                bean.setTvCuisine("Chinese");
                cuisinelist.add(bean);
            } else if (i == 9) {
                bean.setTvCuisine("American");
                cuisinelist.add(bean);
            } else if (i == 10) {
                bean.setTvCuisine("Hawaiian");
                cuisinelist.add(bean);
            } else if (i == 11) {
                bean.setTvCuisine("Brazilian");
                cuisinelist.add(bean);
            } else if (i == 12) {
                bean.setTvCuisine("Cajun Food");
                cuisinelist.add(bean);
            }

        }

        CuisineAdapter adapter = new CuisineAdapter(activity, cuisinelist);
        rvCuisine.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        llContactUs.setOnClickListener(this);
        llAboutUs.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        ivEditProfile.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        tvCuisine.setOnClickListener(this);

    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(navigation_view);
//        replaceFragment(new DeliveryFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_container, fragment);
        transaction.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        tvFName.setText(Preferences.getFname());
        tvLName.setText(Preferences.getLname());
        tvEmail.setText(Preferences.getUseremail());
//        ApplicationHelper.showToast(activity, "Please refresh the list again.");
        if (tabselected == 1) {
            replaceFragment(new NearByFragment());
        } else if (tabselected == 2) {
            replaceFragment(new RatingFragment());
        } else if (tabselected == 3) {
            replaceFragment(new TakeOutFragment());
        }

        if (Preferences.getAddress() != null) {
            tvCurLocation.setText(Preferences.getAddress());
        }
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            Log.e("HomeActivity", "::>>> " + Preferences.getLatitude() + "::" + Preferences.getLongitude());
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(Preferences.getLatitude()),
                    Double.parseDouble(Preferences.getLongitude()), 1);
            if(addresses !=null && !addresses.isEmpty()) {
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String zip = addresses.get(0).getPostalCode();
                String country = addresses.get(0).getCountryName();
                String location = addresses.get(0).getThoroughfare();
                String address = addresses.get(0).getAddressLine(0);
                tvCurLocation.setText(address);
                Log.e("HomeActivity", "::>>location " + city);
            }
//            ApplicationHelper.showToast(activity, "Please re-select the ");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        getAddress();
//        replaceFragment(new DeliveryFragment());
        try {
            Log.e("HomeActivity", "::>>onResume" + Preferences.getGraphlist().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAddress() {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            Log.e("HomeActivity", "::>>> " + latitude + "::" + longitude);
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String zip = addresses.get(0).getPostalCode();
            String country = addresses.get(0).getCountryName();
            String location = addresses.get(0).getThoroughfare();
            String address = addresses.get(0).getAddressLine(0);
            tvCurLocation.setText(address);
            Log.e("HomeActivity", "::>>location " + city);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ivMenu:
                drawerLayout.openDrawer(navigation_view);
                break;

            case R.id.ivEditProfile:
                intent = new Intent(activity, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.ivSearch:
                intent = new Intent(activity, SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.llLogout:
                showDialog();
                break;

            case R.id.llAboutUs:
                intent = new Intent(activity, AboutUsActivity.class);
                intent.putExtra("from", "1");
                startActivity(intent);
                break;

            case R.id.llContactUs:
                intent = new Intent(activity, AboutUsActivity.class);
                intent.putExtra("from", "2");
                startActivity(intent);
                break;

            case R.id.tvCuisine:
                if (count == 0) {
                    rvCuisine.setVisibility(View.VISIBLE);
                    tvCuisine.setTypeface(null, Typeface.BOLD);
                    tvCuisine.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dropdown, 0, 0, 0);
                    count = 1;
                } else {
                    rvCuisine.setVisibility(View.GONE);
                    tvCuisine.setTypeface(null, Typeface.NORMAL);
                    tvCuisine.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dropdown_up, 0, 0, 0);
                    count = 0;
                }
                break;
        }

    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.alert_logout));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Preferences.setFname(null);
                        Preferences.setLname(null);
                        Preferences.setUseremail(null);
                        Preferences.setUserId(null);
                        Preferences.setPhone(null);
                        Preferences.setAddress(null);

                        Preferences.setFilterRate(null);
                        Preferences.setFilterPrice1(null);
                        Preferences.setFilterPrice2(null);
                        Preferences.setFilterPrice3(null);
                        Preferences.setC1(null);
                        Preferences.setC2(null);
                        Preferences.setC3(null);
                        Preferences.setC4(null);
                        Preferences.setC5(null);
                        Preferences.setC6(null);
                        Preferences.setC7(null);
                        Preferences.setC8(null);
                        Preferences.setC9(null);
                        Preferences.setC8(null);
                        Preferences.setC10(null);
                        Preferences.setC11(null);
                        Preferences.setC12(null);
                        Preferences.setC13(null);
                        // positive button logic
                        Intent intent = new Intent(activity, Login_SignupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

        String negativeText = getString(android.R.string.cancel);
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

}
