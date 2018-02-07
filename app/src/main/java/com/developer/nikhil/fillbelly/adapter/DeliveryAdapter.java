package com.developer.nikhil.fillbelly.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.activity.DetailScreenActivity;
import com.developer.nikhil.fillbelly.model.HotelBean;
import java.util.ArrayList;


public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder> {

    private static final String TAG = "DeliveryAdapter.class";
    private Context ctx;
    private ArrayList<HotelBean> hotellist;

    public DeliveryAdapter(Context context, ArrayList<HotelBean> hotellist) {
        this.ctx = context;
        this.hotellist = hotellist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDetail, tvRating;
        public ImageView ivRestaurantPic,mapView;

        public MyViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tvName);
            tvDetail = (TextView) view.findViewById(R.id.tvDetail);
            tvRating = (TextView) view.findViewById(R.id.tvRating);
            ivRestaurantPic = (ImageView) view.findViewById(R.id.ivRestaurantPic);
            mapView= (ImageView) view.findViewById(R.id.mapView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nearby, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Log.e(TAG, "MyOrderAdapter.... " + hotellist.size());
        final HotelBean bean = hotellist.get(position);

        holder.tvName.setText(bean.getName());
        holder.tvDetail.setText(bean.getCuisines());
        holder.tvRating.setText(bean.getUser_rating() + " /5.0");

        Glide.with(ctx).load(bean.getThumb())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_headerr)
                .into(holder.ivRestaurantPic);

        holder.mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String map = "http://maps.google.co.in/maps?q=" + bean.getAddress();

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                ctx.startActivity(i);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailScreenActivity.class);
                intent.putExtra("hotelname", hotellist.get(position).getName());
                intent.putExtra("address", hotellist.get(position).getAddress());
                intent.putExtra("cuisines", hotellist.get(position).getCuisines());
                intent.putExtra("ratings", hotellist.get(position).getUser_rating());
                intent.putExtra("average_cost_for_two", hotellist.get(position).getAverage_cost_for_two());
                intent.putExtra("has_online_delivery", hotellist.get(position).getHas_online_delivery());
                intent.putExtra("price_range", hotellist.get(position).getPrice_range());
                intent.putExtra("votes", hotellist.get(position).getVotes());
                intent.putExtra("id", hotellist.get(position).getId());
                intent.putExtra("image_url", hotellist.get(position).getThumb());
                ctx.startActivity(intent);
            }
        });

    }

    public void updateList(ArrayList<HotelBean> list){
        hotellist = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return hotellist.size();
    }

}
