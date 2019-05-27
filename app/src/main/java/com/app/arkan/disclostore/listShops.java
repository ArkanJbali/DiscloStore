package com.app.arkan.disclostore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class listShops extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shops);
        Toolbar tb =  findViewById(R.id.app_bar);
        //tb.setTitle(getIntent().getStringExtra("Title"));
        //tb.setLogo(R.drawable.disclostore_icon_small);
        ImageView logo = (ImageView) tb.findViewById(R.id.logo);
        TextView title = (TextView) tb.findViewById(R.id.title);
        logo.setBackgroundResource(R.drawable.disclostore_icon_small);
        title.setText(getIntent().getStringExtra("Title"));
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openRatingDialog(View v){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
