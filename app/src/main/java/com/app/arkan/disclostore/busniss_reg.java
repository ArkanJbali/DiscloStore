package com.app.arkan.disclostore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class busniss_reg extends Activity implements OnItemSelectedListener ,View.OnClickListener {
    private ImageView profileImageView;
    private Button pickImage;
    EditText ea,ebp,ef,ebe,ew,esn;
    Button add;
    Button location;
    TextView chooseTime,chooseTime1 ;
    CheckBox su, mo, tu, we, th ,fr, sa;
    String data="";
    String sl="";
    String day="", about="", bphone="", fax="", bemail="", web="", time="", storename="";
    int cat = 0;
    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO = 2;
    DBAdapter db;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private boolean hasImageChanged = false;
    DbHelper dbHelper;

    Bitmap thumbnail;

    @Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busniss_reg);
        db = new DBAdapter(this);
        chooseTime=(TextView)findViewById(R.id.etChooseTime);
        chooseTime1=(TextView)findViewById(R.id.etChooseTime1);
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        pickImage = (Button) findViewById(R.id.pick_image);
       location = (Button) findViewById(R.id.pick_loc);
         ea=(EditText)findViewById(R.id.ea);
         ebp=(EditText)findViewById(R.id.ebp);
         ef=(EditText)findViewById(R.id.ef);
         ebe=(EditText)findViewById(R.id.ebe);
         ew=(EditText)findViewById(R.id.ew);
         esn=findViewById(R.id.esn);
         add=(Button)findViewById(R.id.add);
         su=(CheckBox)findViewById(R.id.chksu);
         mo=(CheckBox)findViewById(R.id.chkmo);
         tu=(CheckBox)findViewById(R.id.chktu);
         we=(CheckBox)findViewById(R.id.chkwe);
         th=(CheckBox)findViewById(R.id.chkth);
         fr=(CheckBox)findViewById(R.id.chkfr);
         sa=(CheckBox)findViewById(R.id.chksa);

        Intent i =getIntent();
        sl=getIntent().getStringExtra("sl");


        add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 storeData();
                 Intent intent = new Intent(busniss_reg.this, login.class);
                 startActivity(intent);
                 Toast.makeText(busniss_reg.this, sl, Toast.LENGTH_SHORT).show();
             }
         });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeData();
                Intent intent = new Intent(busniss_reg.this, STORE_LOCATION.class);
                startActivity(intent);
            }
        });
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(busniss_reg.this,R.style.MyTimePickerDialogStyle, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        chooseTime.setText(" "+hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }
        });
        chooseTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(busniss_reg.this,R.style.MyTimePickerDialogStyle, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        chooseTime1.setText(" "+hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }
        });

        pickImage.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(busniss_reg.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            profileImageView.setEnabled(false);
            ActivityCompat.requestPermissions(busniss_reg.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            profileImageView.setEnabled(true);
        }

        dbHelper = new DbHelper(this);



        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Barber");
            categories.add("Blacksmith");
            categories.add("Book");
            categories.add("Carpenter");
        categories.add("Clothes");
        categories.add("Electrical");
            categories.add("Electronics");
            categories.add("Flowers");
            categories.add("Jewel");
        categories.add("Mechanic");
        categories.add("Optics");
            categories.add("Shoes");
            categories.add("Toys");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        }
        //now the image picker

@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        int item =(int)(id+1);
        cat=item;
        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pick_image:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_act);
               // dialog.setTitle("Title...");
                Button pickImage = (Button) dialog.findViewById(R.id.pickFromGallery);
                // if button is clicked, close the custom dialog
                pickImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                    }
                });
                Button pickCamer = (Button) dialog.findViewById(R.id.pickFromCamera);
                // if button is clicked, close the custom dialog
                pickCamer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAPTURE_PHOTO);
                    }
                });
                Button remove = (Button) dialog.findViewById(R.id.removeImage);
                // if button is clicked, close the custom dialog
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        profileImageView.setImageResource(R.drawable.ic_account_circle_black);
                    }
                });
                dialog.show();
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                profileImageView.setEnabled(true);
            }
        }
    }

    public void setProgressBar(){
        progressBar = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus < 100){
                    progressBarStatus += 30;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                if (progressBarStatus >= 100) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }

            }
        }).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PHOTO){
            if(resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //set Progress Bar
                    setProgressBar();
                    //set profile picture form gallery
                    profileImageView.setImageBitmap(selectedImage);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }else if(requestCode == CAPTURE_PHOTO){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_sort:
                Intent intent = new Intent(this, DetailsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");

        //set Progress Bar
        setProgressBar();
        //set profile picture form camera
        profileImageView.setMaxWidth(200);
        profileImageView.setImageBitmap(thumbnail);

    }

   public void storeData(){
       if(su.isChecked()){ day+="Su ,";}
       if(mo.isChecked()){ day+="Mo ,";}
       if(tu.isChecked()){ day+="Tu ,";}
       if(we.isChecked()){ day+="We ,";}
       if(th.isChecked()){ day+="Th ,";}
       if(fr.isChecked()){ day+="Fr ,";}
       if(sa.isChecked()){ day+="Sa ,";}
        db.open();
       profileImageView.setDrawingCacheEnabled(true);
       profileImageView.buildDrawingCache();
       Bitmap bitmap = profileImageView.getDrawingCache();
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
       byte[] data = baos.toByteArray();

       Toast.makeText(this, "Image saved to DB successfully", Toast.LENGTH_SHORT).show();

        db.insertOwnership(cat,ea.getText().toString(),data,day,ef.getText().toString(),ebp.getText().toString(),
                ebe.getText().toString(), ew.getText().toString(),"location not ready");

        db.insertCategory(cat);

        db.insertStoreinfo(cat,esn.getText().toString(),0,0);

       Toast.makeText(this, "Data saved to DB successfully", Toast.LENGTH_SHORT).show();
        db.close();
   }
    public void dis(){
        if(su.isChecked()){ day+="Su ,";}
        if(mo.isChecked()){ day+="Mo ,";}
        if(tu.isChecked()){ day+="Tu ,";}
        if(we.isChecked()){ day+="We ,";}
        if(th.isChecked()){ day+="Th ,";}
        if(fr.isChecked()){ day+="Fr ,";}
        if(sa.isChecked()){ day+="Sa ,";}
    time=chooseTime.getText().toString()+"-"+chooseTime1.getText().toString();
     about=ea.getText().toString();
     bphone=ebp.getText().toString();
     fax=ef.getText().toString();
     bemail=ebe.getText().toString();
     web=ew.getText().toString();
        Toast.makeText(this, cat, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, about, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, bphone, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, fax, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, bemail, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, day, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, web, Toast.LENGTH_SHORT).show();

    }
}