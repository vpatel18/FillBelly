package com.developer.nikhil.fillbelly.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.nikhil.fillbelly.R;


public class CollectionDetailActivity extends BaseActivity {

    private TextView tvHotelName, tvDescription;
    private ImageView ivBack, ivCollection;
    private static Activity activity;
    private String title, description, image_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_detail);
        activity = CollectionDetailActivity.this;
        findviews();

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        image_url = intent.getStringExtra("image_url");

        tvHotelName.setText(title);
        tvDescription.setText(description);

        Glide.with(activity).load(image_url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_headerr)
                .into(ivCollection);

    }

    private void findviews() {
        tvHotelName = (TextView) findViewById(R.id.tvHotelName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivCollection = (ImageView) findViewById(R.id.ivCollection);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
