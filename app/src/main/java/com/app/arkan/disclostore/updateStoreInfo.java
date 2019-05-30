package com.app.arkan.disclostore;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class updateStoreInfo extends AppCompatActivity {
    String Storename = "",aboutTxt = "", opendayTxt = "", phoneTxt = "", faxTxt = "", emailTxt = "",
    locationTxt = "", websiteTxt = "";
    int StoreID = 0,  category = 0;
    EditText header, about, openday, contact, location, website;
    ImageView img;
    Button sumbit;
    DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_store_info);
        img = findViewById(R.id.img);
        db = new DBAdapter(this);
        header = findViewById(R.id.title);
        about = findViewById(R.id.abouts);
        openday = findViewById(R.id.opens);
        contact = findViewById(R.id.contacts);
        location = findViewById(R.id.location);
        website = findViewById(R.id.website);
        Storename = getIntent().getStringExtra("Storename");
        ImageView backBTN = findViewById(R.id.backBTN);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStoreInfoData();
            }
        });
        getStoreInfoData();
    }

    public void getStoreInfoData() {
        db.open();
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
                location.setText(c.getString(4));
                contact.setText("Phone: "+c.getString(5)+"\n"+"Fax: "+c.getString(6)+"\n"+"Email: "+c.getString(7));
                website.setText(c.getString(8));
                StoreID = Integer.valueOf(c.getString(9));
                category = Integer.valueOf(c.getString(10));
              //  Toast.makeText(getApplicationContext(),StoreID+"",Toast.LENGTH_LONG).show();
            } while (c.moveToNext());
            db.close();
        }

    }

    public void updateStoreInfoData() {
        img.setDrawingCacheEnabled(true);
        img.buildDrawingCache();
        Bitmap bitmap = img.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        db.open();
       Storename = header.getText().toString();
        aboutTxt = about.getText().toString();
        opendayTxt = openday.getText().toString();
       locationTxt = location.getText().toString();
       String contactArr [] = contact.getText().toString().split("\n");
       phoneTxt = contactArr[0].substring(contactArr[0].indexOf(" ")+1);
       faxTxt = contactArr[1].substring(contactArr[1].indexOf(" ")+1);
       emailTxt = contactArr[2].substring(contactArr[2].indexOf(" ")+1);
       websiteTxt = website.getText().toString();
        boolean updated = db.updateOwnerships(StoreID,category,data,aboutTxt,opendayTxt,faxTxt,phoneTxt,emailTxt,websiteTxt,locationTxt,Storename);
        Toast.makeText(getApplicationContext(),"Store Info Updated Successfully",Toast.LENGTH_LONG).show();
        db.close();

        if(updated){
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"An Error Occured When Updated",Toast.LENGTH_LONG).show();
        }
    }
}
