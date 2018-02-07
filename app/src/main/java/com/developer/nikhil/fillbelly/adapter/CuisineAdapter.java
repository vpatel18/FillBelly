package com.developer.nikhil.fillbelly.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.activity.HomeActivity;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.developer.nikhil.fillbelly.model.DrawerBean;

import java.util.ArrayList;


public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.MyViewHolder> {

    private static final String TAG = "CuisineAdapter.class";
    private Context ctx;
    private ArrayList<DrawerBean> cuisinelist;
    private boolean c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13;

    public CuisineAdapter(Context context, ArrayList<DrawerBean> cuisinelist) {
        this.ctx = context;
        this.cuisinelist = cuisinelist;
        Log.e(TAG, "CuisineAdapter...");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCuisine;
        public CheckBox chkCuisine;
        public LinearLayout llCuisine, llRate, llCost;
        public Button btnSave;

        public MyViewHolder(View view) {
            super(view);
            tvCuisine = (TextView) view.findViewById(R.id.tvCuisine);
            chkCuisine = (CheckBox) view.findViewById(R.id.chkCuisine);
            btnSave = (Button) view.findViewById(R.id.btnSave);
            llCost = (LinearLayout) view.findViewById(R.id.llCost);
            llRate = (LinearLayout) view.findViewById(R.id.llRate);
            llCuisine = (LinearLayout) view.findViewById(R.id.llCuisine);
            llCuisine.setVisibility(View.VISIBLE);
            llRate.setVisibility(View.GONE);
            llCost.setVisibility(View.GONE);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Log.e(TAG, "CuisineAdapter.... " + cuisinelist.size());
        DrawerBean bean = cuisinelist.get(position);

        if (cuisinelist.get(position).getTvCuisine().equals("Cajun Food")) {
            holder.btnSave.setVisibility(View.VISIBLE);
        }

        holder.tvCuisine.setText(cuisinelist.get(position).getTvCuisine());
        //in some cases, it will prevent unwanted situations
        holder.chkCuisine.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.chkCuisine.setChecked(cuisinelist.get(position).isSelected());

        holder.chkCuisine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cuisinelist.get(position).isSelected() == false) {
                    //set your object's last status
                    cuisinelist.get(position).setSelected(isChecked);
                    Log.e(TAG, "::>>>> if" + cuisinelist.get(position) + holder.tvCuisine.getText().toString());

                } else {
                    cuisinelist.get(position).setSelected(isChecked);
                    Log.e(TAG, "::>>>> else" + cuisinelist.get(position) + holder.tvCuisine.getText().toString());
                }
            }
        });

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (position == 0) {
                    c1 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c1 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 1) {
                    c2 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c2 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 2) {
                    c3 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c3 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 3) {
                    c4 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c4 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 4) {
                    c5 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c5 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 5) {
                    c6 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c6 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 6) {
                    c7 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c7 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 7) {
                    c8 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c8 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 8) {
                    c9 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c9 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 9) {
                    c10 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c10 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 10) {
                    c11 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c11 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 11) {
                    c12 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c12 + "<<::>> " + cuisinelist.get(position).isSelected());
                } else if (position == 12) {
                    c13 = cuisinelist.get(position).isSelected();
                    Log.e(TAG, "::>> " + c13 + "<<::>> " + cuisinelist.get(position).isSelected());
                }*/

                if (cuisinelist.get(0).isSelected() == true)
                    Preferences.setC1("Indian");
                else
                    Preferences.setC1(null);
                if (cuisinelist.get(1).isSelected() == true)
                    Preferences.setC2("Italian");
                else
                    Preferences.setC2(null);
                if (cuisinelist.get(2).isSelected() == true)
                    Preferences.setC3("French");
                else
                    Preferences.setC3(null);
                if (cuisinelist.get(3).isSelected() == true)
                    Preferences.setC4("Japanese");
                else
                    Preferences.setC4(null);
                if (cuisinelist.get(4).isSelected() == true)
                    Preferences.setC5("Mediterranean");
                else
                    Preferences.setC5(null);
                if (cuisinelist.get(5).isSelected() == true)
                    Preferences.setC6("Mexican");
                else
                    Preferences.setC6(null);
                if (cuisinelist.get(6).isSelected() == true)
                    Preferences.setC7("Morrocon");
                else
                    Preferences.setC7(null);
                if (cuisinelist.get(7).isSelected() == true)
                    Preferences.setC8("Thai");
                else
                    Preferences.setC8(null);
                if (cuisinelist.get(8).isSelected() == true)
                    Preferences.setC9("Chinese");
                else
                    Preferences.setC9(null);
                if (cuisinelist.get(9).isSelected() == true)
                    Preferences.setC10("American");
                else
                    Preferences.setC10(null);
                if (cuisinelist.get(10).isSelected() == true)
                    Preferences.setC11("Hawaiian");
                else
                    Preferences.setC11(null);
                if (cuisinelist.get(11).isSelected() == true)
                    Preferences.setC12("Brazilian");
                else
                    Preferences.setC12(null);
                if (cuisinelist.get(12).isSelected() == true)
                    Preferences.setC13("Cajun Food");
                else
                    Preferences.setC13(null);

                Log.e("CuisineAdapter", "::>>> " + Preferences.getC1() + Preferences.getC2() + Preferences.getC3() +
                        Preferences.getC4() + Preferences.getC5() + Preferences.getC6() + Preferences.getC7() +
                        Preferences.getC8() + Preferences.getC9() + Preferences.getC10() + Preferences.getC11() +
                        Preferences.getC12() + Preferences.getC13());

                ((HomeActivity) ctx).closeDrawer();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return cuisinelist.size();
    }

}
