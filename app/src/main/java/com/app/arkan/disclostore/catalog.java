package com.app.arkan.disclostore;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class catalog extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

    }
    public void onClickShops(View v){
        switch (v.getId()) {
            case R.id.clothesImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Clothes");
                intent.putExtra("categoryid", 5);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.shoesImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Shoes");
                intent.putExtra("categoryid", 12);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.bookImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Books");
                intent.putExtra("categoryid", 3);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.jewelImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Jewels");
                intent.putExtra("categoryid", 9);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.opticsImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Optics");
                intent.putExtra("categoryid", 11);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.toysImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Toys");
                intent.putExtra("categoryid", 13);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.flowerImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Flowers");
                intent.putExtra("categoryid", 8);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.electornicsImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Electronics");
                intent.putExtra("categoryid", 7);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.barberImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Barbers");
                intent.putExtra("categoryid", 1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.carpenterImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Carpenter");
                intent.putExtra("categoryid", 4);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.electricalImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Electrical");
                intent.putExtra("categoryid", 6);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.mechanicImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Mechanics");
                intent.putExtra("categoryid", 10);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.blacksmithImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Blacksmiths");
                intent.putExtra("categoryid", 2);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.blacksmith2Img: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Blacksmiths2");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }




        }
    }
}

