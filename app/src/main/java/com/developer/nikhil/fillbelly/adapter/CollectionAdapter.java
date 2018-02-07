package com.developer.nikhil.fillbelly.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.developer.nikhil.fillbelly.activity.CollectionDetailActivity;
import com.developer.nikhil.fillbelly.activity.DetailScreenActivity;
import com.developer.nikhil.fillbelly.model.CollectionBean;
import com.developer.nikhil.fillbelly.model.HotelBean;

import java.util.ArrayList;


public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {

    private static final String TAG = "CollectionAdapter.class";
    private Context ctx;
    private ArrayList<CollectionBean> hotellist;

    public CollectionAdapter(Context context, ArrayList<CollectionBean> hotellist) {
        this.ctx = context;
        this.hotellist = hotellist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDetail, tvRating;
        public ImageView ivRestaurantPic, ivStar;

        public MyViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tvName);
            tvDetail = (TextView) view.findViewById(R.id.tvDetail);
            tvRating = (TextView) view.findViewById(R.id.tvRating);
            ivRestaurantPic = (ImageView) view.findViewById(R.id.ivRestaurantPic);
            ivStar = (ImageView) view.findViewById(R.id.ivStar);
            ivStar.setVisibility(View.GONE);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nearby, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Log.e(TAG, "CollectionAdapter.... " + hotellist.size());
        CollectionBean bean = hotellist.get(position);

        holder.tvName.setText(bean.getTitle());
        holder.tvDetail.setText(bean.getDescription());
        holder.tvRating.setVisibility(View.GONE);
//        holder.tvRating.setText(bean.getUser_rating() + " /5.0");

        Glide.with(ctx).load(bean.getImage_url())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_headerr)
                .into(holder.ivRestaurantPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, CollectionDetailActivity.class);
                intent.putExtra("title", hotellist.get(position).getTitle());
                intent.putExtra("description", hotellist.get(position).getDescription());
                intent.putExtra("image_url", hotellist.get(position).getImage_url());
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return hotellist.size();
    }

}
