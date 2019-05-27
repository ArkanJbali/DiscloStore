package com.app.arkan.disclostore;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Ownership extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownership_info_update);

    }
    public void storeView(View v){
        startActivity(new Intent(Ownership.this, StoreInfo.class));

    }
    public void updateView(View v){
        startActivity(new Intent(Ownership.this, updateStoreInfo.class));

    }
}
