package com.app.arkan.disclostore;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class listShops extends AppCompatActivity {
    DBAdapter db;
    String storename = "test";
    private Context context = null;
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
        db = new DBAdapter(this);
        db.open();
        getRating(storename);
        ImageView img = findViewById(R.id.image3);
        if(db.get() == null){
            Toast.makeText(this,"Null", Toast.LENGTH_SHORT).show();
        }else {
            img.setImageBitmap(db.get());
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        }
        db.close();
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
                getStores();
                db.open();
                long id=db.getShopId(storename);
                int count = (int)(db.getShopCount(storename));
                count +=1;
                // change ratings -> ratings=ratings + ratings
                db.updateRating(id,ratings,count);
                db.close();
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

    public void getCount(View v){
        db.open();
        String storename = getIntent().getStringExtra("Title").toLowerCase();
        long s= db.getShopsCount(storename);
        Toast.makeText(getApplicationContext(),"count = "+s,Toast.LENGTH_LONG).show();
        db.close();
    }

    //update count & rate after submit rate dialog
    public void getRating(String storename){
        db.open();
        long rateAverage = db.getShopRateSum(storename);
        //set rate text
        TextView rate = findViewById(R.id.rating);
        rate.setText(rateAverage + "");
        db.close();
    }
    public void createShopList(){
        //layout
        TableLayout tableLayout = findViewById(R.id.table_ly);
        context = getApplicationContext();
    }

    public void getStores(){
        db.open();
        Cursor c = db.getAllStore();
        if (c.moveToFirst())
        {
            do {
                DisplayStores(c);
            } while (c.moveToNext());
        }
        db.close();
    }
    public void DisplayStores(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Category ID: " + c.getString(1) + "\n" +
                        "Store Name:  " + c.getString(2)+ "\n" +
                        "Rating:  " + c.getString(3)+ "\n" +
                        "Rates Count:  " + c.getString(4)+ "\n", Toast.LENGTH_LONG).show();
    }

}
