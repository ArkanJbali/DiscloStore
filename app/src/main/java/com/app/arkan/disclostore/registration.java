package com.app.arkan.disclostore;

import android.content.Intent;
import android.database.Cursor;
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
    int role=0;
    EditText username,password,confpassword,email,phone;
    Button reg;
    DBAdapter db;
    RadioButton customerBTN,businessBTN;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        db = new DBAdapter(this);
         reg=(Button)findViewById(R.id.confirm);
        username=(EditText)findViewById(R.id.eun);
        password=(EditText)findViewById(R.id.ep);
        confpassword=(EditText)findViewById(R.id.ecp);
        email=(EditText)findViewById(R.id.ee);
        phone=(EditText)findViewById(R.id.eph);
        final String emailval = email.getText().toString().trim();
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
                    Boolean b =emailval.matches(emailPattern);
                    Toast.makeText(getApplicationContext(), b.toString(), Toast.LENGTH_LONG).show();
                    email.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    email.setError("please enter data",customErrorDrawable);
                }
                else {
                    email.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    email.setError(null);
                }

                if(phone.getText().toString().equals("")){
                    phone.setBackground(getResources().getDrawable(R.drawable.et_bgerr));
                    phone.setError("please enter data",customErrorDrawable);
                }
                else {
                    phone.setBackground(getResources().getDrawable(R.drawable.et_bg));
                    phone.setError(null);
                }
                 customerBTN = findViewById(R.id.radio_customer);
                 businessBTN = findViewById(R.id.radio_business);
                if(customerBTN.isChecked()){
                    role=1;

                }else if(businessBTN.isChecked()){
                    role=2;
                }
                if(!username.getText().toString().equals("") && !password.getText().toString().equals("") &&
                        !confpassword.getText().toString().equals("") && !email.getText().toString().equals("") &&
                        !phone.getText().toString().equals("")){
                    if(!CheckUserRegister()) {
                        userRegistration();
                        Toast.makeText(getApplicationContext(),"User Added Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(registration.this, login.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Email already used",Toast.LENGTH_SHORT).show();
                    }
                }
                if (role==2){
                    Intent intent=new Intent(registration.this,busniss_reg.class);
                    startActivity(intent);
                }
            }
        });

    }
    public boolean CheckUserRegister(){
        db.open();
        EditText email = findViewById(R.id.ee);
        String mail=email.getText().toString();
        Cursor c = db.CheckUserRegister(mail);
        if (c.moveToFirst())
        {
            do {
                if(c.getString(0).equals(mail)){
                    return true;
                }
            } while (c.moveToNext());
        }
        db.close();
        return false;
    }
    public void userRegistration(){
        String un = username.getText().toString();
        String pw = password.getText().toString();
        String cpw = confpassword.getText().toString();
        String mail = email.getText().toString();
        String ph = phone.getText().toString();
        if(pw.equals(cpw)) {
            db.open();
            //role 1-user 2-ownership
            long s1 = db.insertUser(un, mail, pw, ph, role);
            Toast.makeText(getApplicationContext(), s1  + "Users added", Toast.LENGTH_LONG).show();
            db.close();
        }
        if(!pw.equals(cpw)){
            Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
        }
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_customer:
                if (checked)
                    role=1;
                Toast.makeText(getApplicationContext(),"first one is checked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_business:
                if (checked)
                    role=2;
                Toast.makeText(getApplicationContext(),"second one is checked",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}


