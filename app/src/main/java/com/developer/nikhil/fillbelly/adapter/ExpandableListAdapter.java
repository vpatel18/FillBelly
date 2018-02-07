package com.developer.nikhil.fillbelly.adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.activity.HomeActivity;
import com.developer.nikhil.fillbelly.common.Preferences;

import java.util.HashMap;
import java.util.List;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> header; // header titles
    // Child data in format of header title, child title
    private HashMap<String, List<String>> child;
    private String rate;
    private boolean price1, price2, price3, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    @Override
    public int getGroupCount() {
        // Get header size
        return this.header.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // return children count
        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // Get header position
        return this.header.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // This will return the child
        return this.child.get(this.header.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        // Getting header title
        String headerTitle = (String) getGroup(groupPosition);

        // Inflating header layout and setting text
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, parent, false);
        }

        //set content for the parent views
        TextView header_text = (TextView) convertView.findViewById(R.id.header);
        header_text.setText(headerTitle);

        // If group is expanded then change the text into bold and change the
        // icon
        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dropdown, 0, 0, 0);
            header_text.setCompoundDrawablePadding(20);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon
            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dropdown_up, 0, 0, 0);
            header_text.setCompoundDrawablePadding(20);
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // Getting child text
        final String childText = (String) getChild(groupPosition, childPosition);
        // Inflating child layout and setting textview
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, parent, false);
        }

        //set content in the child views
        TextView tvCuisine = (TextView) convertView.findViewById(R.id.tvCuisine);
        TextView tvCost = (TextView) convertView.findViewById(R.id.tvCost);
        Button btnSave = (Button) convertView.findViewById(R.id.btnSave);
        LinearLayout llCuisine = (LinearLayout) convertView.findViewById(R.id.llCuisine);
        LinearLayout llRate = (LinearLayout) convertView.findViewById(R.id.llRate);
        RatingBar ratingbar = (RatingBar) convertView.findViewById(R.id.ratingbar);
        LinearLayout llCost = (LinearLayout) convertView.findViewById(R.id.llCost);
        CheckBox chkCuisine = (CheckBox) convertView.findViewById(R.id.chkCuisine);
        CheckBox chkCost = (CheckBox) convertView.findViewById(R.id.chkCost);

        /*if (groupPosition == 0) {
            llCuisine.setVisibility(View.VISIBLE);
            llRate.setVisibility(View.GONE);
            llCost.setVisibility(View.GONE);
//            Log.e("Expandable", ">>>" + groupPosition + "<<>>" + childPosition);
            tvCuisine.setText(childText);
            if (childText.equals("Indian"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Italian"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("French"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Japanese"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Mediterranean"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Mexican"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Morrocon"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Thai"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Chinese"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("American"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Hawaiian"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Brazilian"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("Cajun Food")) {
                btnSave.setVisibility(View.VISIBLE);
            }

            notifyDataSetChanged();

        } else*/ if (groupPosition == 0) {
            llRate.setVisibility(View.GONE);
            llCuisine.setVisibility(View.GONE);
            llCost.setVisibility(View.VISIBLE);
            Log.e("Expandable", ">>>" + groupPosition + "<<>>" + childPosition);
            tvCost.setText(childText);
            if (childText.equals("$"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("$$"))
                btnSave.setVisibility(View.GONE);
            else if (childText.equals("$$$"))
                btnSave.setVisibility(View.VISIBLE);

            notifyDataSetChanged();

        } else {
            if (Preferences.getFilterRate() != null) {
                ratingbar.setRating(Float.parseFloat(Preferences.getFilterRate()));
            }

            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    Log.e("Rating ", "::>> " + ratingBar.getRating());
                    rate = String.valueOf(ratingBar.getRating());
                }
            });
            llRate.setVisibility(View.VISIBLE);
            llCost.setVisibility(View.GONE);
            llCuisine.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);
        }
        notifyDataSetChanged();

//        chkCuisine.setOnCheckedChangeListener(null);

        /*chkCuisine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("ExpandableListView", "chkCuisine::>> " + childPosition);
                *//*if (b) {
                    if (child.containsKey(groupPosition)) {
                        int childmap = child.get(groupPosition);
                        childmap += (1 << childPosition);
                    }

                }*//*

                if (childPosition == 0)
                    c1 = compoundButton.isChecked();
                else if (childPosition == 1)
                    c2 = compoundButton.isChecked();
                else if (childPosition == 2)
                    c3 = compoundButton.isChecked();
                else if (childPosition == 3)
                    c4 = compoundButton.isChecked();
                else if (childPosition == 4)
                    c5 = compoundButton.isChecked();
                else if (childPosition == 5)
                    c6 = compoundButton.isChecked();
                else if (childPosition == 6)
                    c7 = compoundButton.isChecked();
                else if (childPosition == 7)
                    c8 = compoundButton.isChecked();
                else if (childPosition == 8)
                    c9 = compoundButton.isChecked();
                else if (childPosition == 9)
                    c10 = compoundButton.isChecked();
                else if (childPosition == 10)
                    c11 = compoundButton.isChecked();
                else if (childPosition == 11)
                    c12 = compoundButton.isChecked();
                else if (childPosition == 12)
                    c13 = compoundButton.isChecked();

                notifyDataSetChanged();
            }
        });

        notifyDataSetChanged();*/

        chkCost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e("ExpandableListView", "chkCost::>> " + childPosition);
                if (childPosition == 0) {
                    Log.e("Expan", "::>> " + childPosition + compoundButton.isChecked());
                    price1 = compoundButton.isChecked();
                } else if (childPosition == 1) {
                    Log.e("Expan", "::>> " + childPosition + compoundButton.isChecked());
                    price2 = compoundButton.isChecked();
                } else {
                    Log.e("Expan", "::>> " + childPosition + compoundButton.isChecked());
                    price3 = compoundButton.isChecked();
                }
            }
        });

        notifyDataSetChanged();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setFilterRate(rate);
                if (price1 == true) {
                    Preferences.setFilterPrice1("1");
                } else {
                    Preferences.setFilterPrice1(null);
                }
                if (price2 == true) {
                    Preferences.setFilterPrice2("2");
                } else {
                    Preferences.setFilterPrice2(null);
                }
                if (price3 == true) {
                    Preferences.setFilterPrice3("3");
                } else {
                    Preferences.setFilterPrice3(null);
                }

                /*if (c1 == true)
                    Preferences.setC1("Indian");
                else
                    Preferences.setC1(null);
                if (c2 == true)
                    Preferences.setC2("Italian");
                else
                    Preferences.setC2(null);
                if (c3 == true)
                    Preferences.setC3("French");
                else
                    Preferences.setC3(null);
                if (c4 == true)
                    Preferences.setC4("Japanese");
                else
                    Preferences.setC4(null);
                if (c5 == true)
                    Preferences.setC5("Mediterranean");
                else
                    Preferences.setC5(null);
                if (c6 == true)
                    Preferences.setC6("Mexican");
                else
                    Preferences.setC6(null);
                if (c7 == true)
                    Preferences.setC7("Morrocon");
                else
                    Preferences.setC7(null);
                if (c8 == true)
                    Preferences.setC8("Thai");
                else
                    Preferences.setC8(null);
                if (c9 == true)
                    Preferences.setC9("Chinese");
                else
                    Preferences.setC9(null);
                if (c10 == true)
                    Preferences.setC10("American");
                else
                    Preferences.setC10(null);
                if (c11 == true)
                    Preferences.setC11("Hawaiian");
                else
                    Preferences.setC11(null);
                if (c12 == true)
                    Preferences.setC12("Brazilian");
                else
                    Preferences.setC12(null);
                if (c13 == true)
                    Preferences.setC13("Cajun Food");
                else
                    Preferences.setC13(null);

                Log.e("Expa", "::>>> " + Preferences.getC1() + Preferences.getC2() + Preferences.getC3() +
                        Preferences.getC4() + Preferences.getC5() + Preferences.getC6() + Preferences.getC7() +
                        Preferences.getC8() + Preferences.getC9() + Preferences.getC10() + Preferences.getC11() +
                        Preferences.getC12() + Preferences.getC13());
                notifyDataSetChanged();*/

                ((HomeActivity) _context).closeDrawer();

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
//        notifyDataSetChanged();
        return true;
    }

}
