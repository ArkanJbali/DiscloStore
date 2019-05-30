package com.app.arkan.disclostore;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Ownership extends AppCompatActivity {
    String storeName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ownership_info_update);
        TextView storename = findViewById(R.id.storeName);
        storeName = getIntent().getStringExtra("StoreName");
       storename.setText(storeName);
    }
    public void storeView(View v){
        Intent i =new Intent(Ownership.this, StoreInfo.class);
        i.putExtra("Storename", storeName);
        startActivity(i);

    }
    public void updateView(View v){
        Intent i =new Intent(Ownership.this, updateStoreInfo.class);
        i.putExtra("Storename", storeName);
        startActivity(i);

    }
}
