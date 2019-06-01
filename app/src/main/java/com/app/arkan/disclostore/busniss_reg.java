package com.app.arkan.disclostore;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class busniss_reg extends Activity implements OnItemSelectedListener ,View.OnClickListener {
    private ImageView profileImageView;
    private Button pickImage;
    EditText enterAbout,enterBphone,enterFax,enterBemail,enterUrl,storeName;
    Button add,location;
    TextView chooseTime,chooseTime1 ;
    CheckBox su, mo, tu, we, th ,fr, sa;
    String day="",sl="", about="", bphone="", fax="", bemail="", web="", time="", storename="";
    int cat = 0;
    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO = 2;
    DBAdapter db;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private boolean hasImageChanged = false;
    DbHelper dbHelper;
    String phonePattern=  "^[(0)]{1}[0-9\\s.\\/-]{9}$";
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Bitmap thumbnail;
    int flag=0,storeVal = 0;
    String webURL;
    @Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busniss_reg);
        db = new DBAdapter(this);
        dbHelper = new DbHelper(this);

        chooseTime=(TextView)findViewById(R.id.etChooseTime);
        chooseTime1=(TextView)findViewById(R.id.etChooseTime1);
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        pickImage = (Button) findViewById(R.id.pick_image);
        location = (Button) findViewById(R.id.pick_loc);
        enterAbout=(EditText)findViewById(R.id.enterAbout);
        enterBphone=(EditText)findViewById(R.id.enterBphone);
        enterFax=(EditText)findViewById(R.id.enterFax);
        enterBemail=(EditText)findViewById(R.id.enterBemail);
        enterUrl=(EditText)findViewById(R.id.enterUrl);
         storeName=findViewById(R.id.storename);
         add=(Button)findViewById(R.id.add);
         su=(CheckBox)findViewById(R.id.chksu);
         mo=(CheckBox)findViewById(R.id.chkmo);
         tu=(CheckBox)findViewById(R.id.chktu);
         we=(CheckBox)findViewById(R.id.chkwe);
         th=(CheckBox)findViewById(R.id.chkth);
         fr=(CheckBox)findViewById(R.id.chkfr);
         sa=(CheckBox)findViewById(R.id.chksa);
        final Drawable customErrorDrawable = getResources().getDrawable(R.drawable.err);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

        //get location position(latitude,longitude) from location activity
        sl=getIntent().getStringExtra("location");


        add.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
             @Override
             public void onClick(View v) {
                 if (Pattern.compile(phonePattern).matcher(enterBphone.getText().toString()).matches()) {
                     enterBphone.setBackground(getResources().getDrawable(R.drawable.et_bg));
                     enterBphone.setError(null);
                 } else {
                     enterBphone.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                     enterBphone.setError("Enter valid number",customErrorDrawable);
                     storeVal = 1;
                     Log.d("ErrorVal","inside bPhone");
                 }

                 if(enterAbout.getText().toString().length()<6){
                     enterAbout.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                     enterAbout.setError("your details is too short",customErrorDrawable);
                     storeVal = 1;
                     Log.d("ErrorVal","inside about");
                 }
                 else {
                     enterAbout.setBackground(getResources().getDrawable(R.drawable.et_bg));
                     enterAbout.setError(null);

                 }

                 if(storeName.getText().toString().length()<=3){
                     storeName.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                     storeName.setError("your store name is too short",customErrorDrawable);
                     storeVal = 1;
                     Log.d("ErrorVal","inside storeName");
                 }
                 else {
                     storeName.setBackground(getResources().getDrawable(R.drawable.et_bg));
                     storeName.setError(null);

                 }
                 String emailval = enterBemail.getText().toString().trim();
                 Boolean matchMail =emailval.matches(emailPattern);
                 if(matchMail.toString().equals("false")){
                     enterBemail.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                     enterBemail.setError("please enter valid email",customErrorDrawable);
                     storeVal = 1;
                     Log.d("ErrorVal","inside bEmail:"+emailval);
                 }
                 else {
                     enterBemail.setBackground(getResources().getDrawable(R.drawable.et_bg));
                     enterBemail.setError(null);

                 }
                 if(!su.isChecked()&&!mo.isChecked()&&!tu.isChecked()&&!tu.isChecked()&&!we.isChecked()
                         &&!th.isChecked()&&!fr.isChecked()&&!sa.isChecked()){
                     flag=1;
                 }
                 if(flag==1){
                     flag=0;
                     storeVal = 1;
                     Log.d("ErrorVal","inside days");
                    Toast.makeText(getApplicationContext(),"NO DAYES SELECTED",Toast.LENGTH_SHORT).show();

                 }
                if(chooseTime1.getText().toString().equals("Pick time")){
                    Toast.makeText(getApplicationContext(),"check close hour",Toast.LENGTH_SHORT).show();
                    storeVal = 1;

                    Log.d("ErrorVal","inside pickTime2");
                }
                 if(chooseTime.getText().toString().equals("Pick time")){
                     Toast.makeText(getApplicationContext(),"check open hour",Toast.LENGTH_SHORT).show();
                     storeVal = 1;

                     Log.d("ErrorVal","inside pickTime1");
                 }

                 webURL= "http://"+enterUrl.getText().toString();
                 if( !Patterns.WEB_URL.matcher(webURL).matches()){
                     enterUrl.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                     enterUrl.setError("please enter valid website",customErrorDrawable);
                     storeVal = 1;
                     Log.d("ErrorVal","inside website");
                 }
                 else {
                     enterUrl.setBackground(getResources().getDrawable(R.drawable.et_bg));
                     enterUrl.setError(null);
                 }
                if(storeVal == 0){
                    storeData();
                }else{
                    Toast.makeText(busniss_reg.this, "An Error Occurred!, please validate the fields", Toast.LENGTH_SHORT).show();
                    storeVal=0;
                }

             }

         });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        categories.add("Restaurant");
        categories.add("Shoes");
        categories.add("Toys");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        }
@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        int item =(int)(id+1);
        cat=item;
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
    //grant permission to get image not in Manifest(user privacy)
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

    //getting image from camera or gallery activity
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






    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");

        //set Progress Bar
        setProgressBar();
        //set profile picture form camera
        profileImageView.setMaxWidth(200);
        profileImageView.setImageBitmap(thumbnail);

    }
    //store data into DB
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

       storename = storeName.getText().toString();
       String pickTime1 = chooseTime.getText().toString();
       String pickTime2 = chooseTime1.getText().toString();
       day +="\n" + pickTime1 +" - " +pickTime2;
        long id = db.insertOwnerships(cat,enterAbout.getText().toString(),data,day,enterFax.getText().toString(),enterBphone.getText().toString(),
                enterBemail.getText().toString(), webURL,sl,storename,1,1);

        db.insertCategory(cat);
        db.insertStoreinfo(cat,storename,1,1);
        if(id >= 0) {
            Toast.makeText(this, "Data saved to DB successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(busniss_reg.this, login.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "An Error Occurred!, please check the fields", Toast.LENGTH_SHORT).show();
        }
        db.close();

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
}