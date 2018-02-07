package com.developer.nikhil.fillbelly.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.developer.nikhil.fillbelly.R;


public class AboutUsActivity extends BaseActivity {

    private Toolbar toolbar;
    private ImageView ivBack;
    private TextView tvTitle, tvContent;
    private static Activity activity;
    private LinearLayout llAboutUs, llContactUs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        activity = AboutUsActivity.this;

        findviews();

        String from = getIntent().getStringExtra("from");

        if (from.equals("1")) {
            tvTitle.setText("About Us");
            llAboutUs.setVisibility(View.VISIBLE);
            llContactUs.setVisibility(View.GONE);
        } else if (from.equals("2")) {
            tvTitle.setText("Contact Us");
            llContactUs.setVisibility(View.VISIBLE);
            llAboutUs.setVisibility(View.GONE);
        }

    }

    private void findviews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvContent = (TextView) findViewById(R.id.tvContent);
        llContactUs = (LinearLayout) findViewById(R.id.llContactUs);
        llAboutUs = (LinearLayout) findViewById(R.id.llAboutUs);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
