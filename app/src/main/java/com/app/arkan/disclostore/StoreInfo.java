package com.app.arkan.disclostore;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StoreInfo extends AppCompatActivity {
    Button gotourl,gotoloc;
    TextView header, about, openday, contact;
    String url1 ="http:\\www.google.com" ;
    String location;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_info);
        header = findViewById(R.id.title);
        about = findViewById(R.id.abouts);
        openday = findViewById(R.id.opens);
        contact = findViewById(R.id.contacts_i);
        dispstore();
        gotourl = (Button) findViewById(R.id.gotourl);
        gotoloc = (Button) findViewById(R.id.location);
        gotoloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StoreInfo.this, Get_store_loc.class);
                i.putExtra("location", location);
                startActivity(i);
            }
        });
        gotourl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StoreInfo.this, R.style.Widget_AppCompat_ActionBar_Solid);

                WebView wv = new WebView(StoreInfo.this);
                wv.loadUrl(url1);
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);

                        return true;
                    }
                });

                alert.setView(wv);
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();

            }
        });

    }

    public void dispstore() {
        db = new DBAdapter(this);
        db.open();
        String Storename = getIntent().getStringExtra("Storename");
        Cursor c = db.getShop(Storename);
        c.moveToFirst();
        if (c.getCount() > 0) {
            // Toast.makeText(getApplicationContext(),categoryid+","+c.getString(1)+c.getString(2),Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "no rows in DB", Toast.LENGTH_LONG).show();
        }
        if (c.moveToFirst()) {
            do {
                byte[] image = c.getBlob(0);
                Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
                ImageView img = findViewById(R.id.img);
                if(bmp == null){
                    Toast.makeText(this,"Null", Toast.LENGTH_SHORT).show();
                }else {
                    img.setImageBitmap(bmp);
                    // Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                }
                header.setText(c.getString(1));
                about.setText(c.getString(2));
                openday.setText(c.getString(3));
                location= c.getString(4);
                contact.setText("Phone: "+c.getString(5)+"\n"+"Fax: "+c.getString(6)+"\n"+"Email: "+c.getString(7));
                url1=(c.getString(8));
            } while (c.moveToNext());
            db.close();
        }
    }
}
