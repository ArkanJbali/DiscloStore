package com.app.arkan.disclostore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class listShops extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shops);
        Toolbar tb =  findViewById(R.id.app_bar);
        tb.setTitle(getIntent().getStringExtra("Title"));
        tb.setLogo(R.drawable.disclostore_icon_small);
        setSupportActionBar(tb);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void openRatingDialog(View v){
       // startActivity(new Intent(listShops.this,Main.class));
        GlobalUtils.showDialog(this, new DialogCallback() {
            @Override
            public void callback(int ratings) {
                Toast.makeText(getApplicationContext(),ratings + " ",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void rowClick(View v){
        startActivity(new Intent(listShops.this,StoreInfo.class));
    }
}
