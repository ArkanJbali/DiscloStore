package com.app.arkan.disclostore;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText un,pass;
    RelativeLayout rellay1, rellay2;
    static int ROLE_FLAG=0;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBAdapter(this);
//        db.open();
//db.updateTable();
//db.close();
        rellay1 =  findViewById(R.id.rellay1);
        rellay2 =  findViewById(R.id.rellay2);
        un = findViewById(R.id.un);
        pass = findViewById(R.id.pw);
        final Drawable customErrorDrawable = getResources().getDrawable(R.drawable.err);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
        Button loginBTN = findViewById(R.id.btn_login);
        Button signupBTN = findViewById(R.id.signup_btn);
        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,registration.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.fade_in);
            }
        });
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(un.getText().toString().equals("")){
                    un.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    un.setError("please enter data",customErrorDrawable);
                }
                else {
                    un.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    un.setError(null);
                }
                if(pass.getText().toString().equals("")){
                    pass.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    pass.setError("please enter data",customErrorDrawable);
                }
                else {
                    pass.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    pass.setError(null);

                }
                if(!un.getText().toString().equals("") && !pass.getText().toString().equals("")){
                   if( CheckUserLogin()) {
                       if(ROLE_FLAG == 1) {
                           startActivity(new Intent(login.this, catalog.class));
                           overridePendingTransition(R.anim.slide_out_left, R.anim.fade_in);
                       }
                       if(ROLE_FLAG == 2) {
                           Toast.makeText(getApplicationContext(),"Go to ownership activity", Toast.LENGTH_LONG).show();
                           startActivity(new Intent(login.this, Ownership.class));
                       }
                   }else{
                       Toast.makeText(getApplicationContext(),"Invalid UserName or Password !!!", Toast.LENGTH_LONG).show();
                   }
                }

            }
        });
    }

    public boolean CheckUserLogin(){
        db.open();

        EditText email = findViewById(R.id.un);
        EditText pass = findViewById(R.id.pw);
        String mail=email.getText().toString();
        String password = pass.getText().toString();
        Cursor c = db.CheckUserLogin(mail,password);
        if (c.moveToFirst())
        {
            do {
                if(c.getString(0).equals(mail) && c.getString(1).equals(password)){
                 //   Toast.makeText(getApplicationContext(),"Logged in"+c.getString(2),Toast.LENGTH_LONG).show();
                    ROLE_FLAG = Integer.valueOf(c.getString(2));
                    return true;
                }
            } while (c.moveToNext());
        }
        db.close();
        return false;
    }
}
