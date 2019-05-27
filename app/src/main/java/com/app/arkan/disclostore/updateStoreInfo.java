package com.app.arkan.disclostore;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class updateStoreInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_store_info);
        Toolbar tb =  findViewById(R.id.app_bar);
        ImageView logo = (ImageView) tb.findViewById(R.id.logo);
        TextView title = (TextView) tb.findViewById(R.id.title);
        logo.setBackgroundResource(R.drawable.disclostore_icon_small);
        title.setText("Update");
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
