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
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.shoesImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Shoes");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.bookImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Books");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.jewelImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Jewels");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.opticsImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Optics");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.toysImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Toys");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.flowerImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Flowers");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.electornicsImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Electronics");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.barberImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Barbers");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.carpenterImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Carpenter");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.electricalImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Electrical");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.mechanicImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Mechanics");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
                break;
            }
            case R.id.blacksmithImg: {
                Intent intent = new Intent(catalog.this, listShops.class);
                intent.putExtra("Title", "Blacksmiths");
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

