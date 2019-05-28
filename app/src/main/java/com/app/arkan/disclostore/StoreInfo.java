package com.app.arkan.disclostore;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class StoreInfo extends AppCompatActivity {
    Button gotourl;
    TextView header,about,openday,contact,location;
    String url = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_info);
        header = findViewById(R.id.title);
        about = findViewById(R.id.about);
        openday = findViewById(R.id.opens);
        contact = findViewById(R.id.contacts_i);
        location = findViewById(R.id.location_i);

        gotourl=(Button)findViewById(R.id.gotourl);
        gotourl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StoreInfo.this,R.style.Widget_AppCompat_ActionBar_Solid);

                WebView wv = new WebView(StoreInfo.this);
                wv.loadUrl("http:\\www.google.com");
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
    }

