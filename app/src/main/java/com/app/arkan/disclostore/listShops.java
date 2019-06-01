package com.app.arkan.disclostore;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
public class listShops extends AppCompatActivity {
    DBAdapter db;
    String storeNameTxt = "";
    private Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shops);

        //creating customize toolbar that included in xml file + customize style from manifest
        Toolbar tb =  findViewById(R.id.app_bar);
        ImageView logo = (ImageView) tb.findViewById(R.id.logo);
        TextView title = (TextView) tb.findViewById(R.id.title);
        logo.setBackgroundResource(R.drawable.disclostore_icon_small);
        title.setText(getIntent().getStringExtra("Title"));
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = new DBAdapter(this);

        createShopList();
    }


    public void openRatingDialog(final String sName){
        GlobalUtils.showDialog(this, new DialogCallback() {
            @Override
            public void callback(int ratings) {
                db.open();
               int rate =  db.getShopRating(sName);
               int counter = db.getShopCounter(sName);
                Log.d("Rateee","Name:"+sName);
               rate = rate + ratings;
               counter++;

               if(rate==0){
                   rate = 1;
               }
                //get ratings from table add to
                Boolean s=db.updateRating(sName,rate,counter);
               if(s) {
                   Toast.makeText(getApplicationContext(), "Rate is updated" + rate / counter, Toast.LENGTH_LONG).show();
               }
                db.close();
            }
        });
    }
    public void rowClick(String storename){
        Intent i = new Intent(listShops.this,StoreInfo.class);
        i.putExtra("Storename",storename);
        startActivity(i);
    }




        //create shop list programmatically by getting data from DB
    public void createShopList(){
        //layout
        TableLayout tableLayout = findViewById(R.id.table_ly);

        context = getApplicationContext();
        db.open();
        int categoryid = getIntent().getIntExtra("categoryid",0); // getting id from catalog activity
            Cursor c = db.getListShopRow(categoryid);
        c.moveToFirst();
        if(c.getCount() > 0)
        {
            if (c.moveToFirst())
            {
                do {

                    TableRow tableRow = new TableRow(this);
                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    tableRow.setLayoutParams(layoutParams);
                    ImageView img = new ImageView(this);

                    //db.getString(1) its equal to store name
                    if(db.get(c.getString(1)) == null){
                        Toast.makeText(this,"Null", Toast.LENGTH_SHORT).show();
                    }else {
                        img.setImageBitmap(db.get(c.getString(1)));
                        // Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                    }

                    img.setMaxWidth(50);
                    img.setMaxHeight(50);
                    final TextView storename = new TextView(this);
                    storeNameTxt=c.getString(1);
                    storename.setText(storeNameTxt);
                    storename.setTextColor(getResources().getColor(R.color.black));
                    storename.setTextSize(25);
                    TextView blankTxt = new TextView(this);
                    blankTxt.setText("            ");
                    blankTxt.setTextColor(getResources().getColor(R.color.black));
                    blankTxt.setTextSize(25);
                    TextView blankTxt1 = new TextView(this);
                    blankTxt1.setText("       ");
                    blankTxt1.setTextColor(getResources().getColor(R.color.black));
                    blankTxt1.setTextSize(25);
                    ImageView rating = new ImageView(this);
                    rating.setBackgroundResource(R.drawable.rating);
                    rating.setMaxWidth(50);
                    rating.setMaxHeight(50);
                    rating.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String name = storename.getText().toString();
                            openRatingDialog(name);
                        }
                    });
                    tableRow.addView(img);

                    TextView ratingText = new TextView(this);
                    int average = db.getShopRating(storeNameTxt)/db.getShopCounter(storeNameTxt);
                    ratingText.setText(average+"/4");
                    ratingText.setTextColor(getResources().getColor(R.color.black));
                    ratingText.setTextSize(18);
                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String name = storename.getText().toString();
                            rowClick(name);
                        }
                    });
                    tableRow.addView(blankTxt1);
                    tableRow.addView(storename);
                    tableRow.addView(blankTxt);
                    tableRow.addView(ratingText);
                    tableRow.addView(rating);

                    tableRow.setPadding(10,10,10,10);
                    tableLayout.addView(tableRow);

                } while (c.moveToNext());
            }
           // Toast.makeText(getApplicationContext(),categoryid+","+c.getString(1)+c.getString(2),Toast.LENGTH_LONG).show();
        }else{
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(layoutParams);
            final TextView storename = new TextView(this);
            storename.setText("No Shops Available...");
            storename.setTextColor(getResources().getColor(R.color.black));
            storename.setTextSize(20);
            tableRow.addView(storename);

            tableLayout.addView(tableRow);
           //Toast.makeText(getApplicationContext(),"no rows in DB",Toast.LENGTH_LONG).show();
        }


            db.close();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
