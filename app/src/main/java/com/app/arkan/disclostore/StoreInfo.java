package com.app.arkan.disclostore;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StoreInfo extends AppCompatActivity {
    private TextView stickyView;
    private ListView listView;
    private View heroImageView;
    private View stickyViewSpacer;
    private int MAX_ROWS = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_info);

//        /* Initialise list view, hero image, and sticky view */
//        listView = (ListView) findViewById(R.id.listView);
//        heroImageView = findViewById(R.id.heroImageView);
//        stickyView = (TextView) findViewById(R.id.stickyView);
//
//        /* Inflate list header layout */
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View listHeader = inflater.inflate(R.layout.list_header, null);
//        stickyViewSpacer = listHeader.findViewById(R.id.stickyViewPlaceholder);
//
//        /* Add list view header */
//        listView.addHeaderView(listHeader);
//
//        /* Handle list View scroll events */
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                /* Check if the first item is already reached to top.*/
//                if (listView.getFirstVisiblePosition() == 0) {
//                    View firstChild = listView.getChildAt(0);
//                    int topY = 0;
//                    if (firstChild != null) {
//                        topY = firstChild.getTop();
//                    }
//
//                    int heroTopY = stickyViewSpacer.getTop();
//                    stickyView.setY(Math.max(0, heroTopY + topY));
//
//                    /* Set the image to scroll half of the amount that of ListView */
//                    heroImageView.setY(topY * 0.5f);
//                }
//            }
//        });
//
//
//        /* Populate the ListView with sample data */
//        List<String> modelList = new ArrayList<>();
//        for (int i = 0; i < MAX_ROWS; i++) {
//            modelList.add("List item " + i);
//
//        }
//
//       ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_row, modelList);
//       listView.setAdapter(adapter);
    }
    }

