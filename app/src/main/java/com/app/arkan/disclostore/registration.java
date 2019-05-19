package com.app.arkan.disclostore;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class registration extends AppCompatActivity {
    int x=0;
    EditText username,password,confpassword,email,phone,location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        setContentView(R.layout.activity_registeration);
        Button reg=(Button)findViewById(R.id.reg);
        username=(EditText)findViewById(R.id.eun);
        password=(EditText)findViewById(R.id.ep);
        confpassword=(EditText)findViewById(R.id.ecp);
        email=(EditText)findViewById(R.id.ee);
        phone=(EditText)findViewById(R.id.eph);
        location=(EditText)findViewById(R.id.el);
        final Drawable customErrorDrawable = getResources().getDrawable(R.drawable.err);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());


        reg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")){
                    username.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    username.setError("please enter data",customErrorDrawable);
                }
                else {
                    username.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    username.setError(null);
                }
                if(password.getText().toString().equals("")){
                    password.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    password.setError("please enter data",customErrorDrawable);
                }
                else {
                    password.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    password.setError(null);
                }
                if(confpassword.getText().toString().equals("")){
                    confpassword.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    confpassword.setError("please enter data",customErrorDrawable);
                }
                else {
                    confpassword.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    confpassword.setError(null);
                }
                if(email.getText().toString().equals("")){
                    email.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    email.setError("please enter data",customErrorDrawable);
                }
                else {
                    email.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    email.setError(null);
                }
                if(location.getText().toString().equals("")){
                    location.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    location.setError("please enter data",customErrorDrawable);
                }
                else {
                    location.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    location.setError(null);
                }
                if(phone.getText().toString().equals("")){
                    phone.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    phone.setError("please enter data",customErrorDrawable);
                }
                else {
                    phone.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    phone.setError(null);
                }
                if (x==2){
                    Intent intent=new Intent(registration.this,Main.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_customer:
                if (checked)
                    x=1;
                Toast.makeText(getApplicationContext(),"first one is checked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_business:
                if (checked)
                    x=2;
                Toast.makeText(getApplicationContext(),"second one is checked",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}


