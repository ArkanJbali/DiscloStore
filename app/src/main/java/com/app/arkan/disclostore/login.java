package com.app.arkan.disclostore;
import android.content.Intent;
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

public class login extends AppCompatActivity {
    EditText un,pass;
    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        un=(EditText)findViewById(R.id.un);
        pass=(EditText)findViewById(R.id.pw);
        final Drawable customErrorDrawable = getResources().getDrawable(R.drawable.err);
        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
        Button loginBTN = (Button)findViewById(R.id.btn_login);
        Button signupBTN = (Button)findViewById(R.id.signup_btn);
        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,registration.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.hold);
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
                    startActivity(new Intent(login.this,catalog.class));
                    overridePendingTransition(R.anim.slide_out_left, R.anim.hold);

                }

            }
        });
    }
}
