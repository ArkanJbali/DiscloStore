package com.app.arkan.disclostore;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DBActivity extends Activity {
    /** Called when the activity is first created. */
    DBAdapter db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_act);

         db = new DBAdapter(this);


    }
    public void upd(View v){
        db.open();
        //db.updateTable();
       db.insertStoreinfo(1,"test",0,0);
        db.close();
    }
    public void addUser(View v){
        db.open();
        //role 1-user 2-ownership
        long s1=db.insertUser("Arkan","arkan.1997@gmail.com","123456","0524033910",1);
        long s2=db.insertUser("Shlomo","shlomo@gmail.com","123456","21341",2);
        Toast.makeText(getApplicationContext(),s1 + " " + s2 + "Users added",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void getUsers(View v){
        db.open();
        Cursor c = db.getAllUsers();
        if (c.moveToFirst())
        {
            do {
                DisplayUser(c);
            } while (c.moveToNext());
        }
        db.close();
    }
    public void CheckUsers(View v){
        db.open();
        EditText email = findViewById(R.id.email);
        String mail=email.getText().toString();
        Cursor c = db.CheckUser(mail);
        if (c.moveToFirst())
        {
            do {
               // Toast.makeText(getApplicationContext(),c.getString(0)+"Logged in"+c.getString(1),Toast.LENGTH_LONG).show();
               if(c.getString(0).equals(mail)){
                   Toast.makeText(getApplicationContext(),"Logged in"+c.getString(1),Toast.LENGTH_LONG).show();
               }
            } while (c.moveToNext());
        }
        db.close();
    }
    public void DisplayUser(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Email:  " + c.getString(2)+ "\n" +
                        "Password:  " + c.getString(3)+ "\n" +
                        "Phone:  " + c.getString(4)+ "\n" +
                        "Role:  " + c.getString(5)+ "\n",
                Toast.LENGTH_LONG).show();
    }
    public void addOwnership(View v){
        db.open();
        //role 1-user 2-ownership
       // long s1=db.insertOwnership(1,"about","img1","12-00-22","05233","321","s@g.com","www.com","Haifa");
      //  long s2=db.insertOwnership(2,"about","img2","99-00-22","0123","444","s@g.com","www.com","Haifa");

      //  Toast.makeText(getApplicationContext(),s1 + " " + s2 + "added",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void getOwnership(View v){
        db.open();
        Cursor c = db.getAllOwnership();
        if (c.moveToFirst())
        {
            do {
                DisplayOwnership(c);
            } while (c.moveToNext());
        }
        db.close();
    }
    public void getStores(View v){
        db.open();
        Cursor c = db.getAllStore();
        if (c.moveToFirst())
        {
            do {
                DisplayStores(c);
            } while (c.moveToNext());
        }
        db.close();
    }
    public void DisplayStores(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Category ID: " + c.getString(1) + "\n" +
                        "Store Name:  " + c.getString(2)+ "\n" +
                        "Rating:  " + c.getString(3)+ "\n" +
                        "Rates Count:  " + c.getString(4)+ "\n", Toast.LENGTH_LONG).show();
    }
    public void getCategories(View v){
        db.open();
        Cursor c = db.getAllCategories();
        if (c.moveToFirst())
        {
            do {
                DisplayCategories(c);
            } while (c.moveToNext());
        }
        db.close();
    }
    public void DisplayCategories(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Category ID: " + c.getString(1) + "\n", Toast.LENGTH_LONG).show();
    }
    public void DisplayOwnership(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Category: " + c.getString(1) + "\n" +
//                        "Image:  " + c.getString(2)+ "\n" +
                        "About:  " + c.getString(3)+ "\n" +
                        "fax:  " + c.getString(4)+ "\n" +
                        "Bphone:  " + c.getString(5)+ "\n"+
                        "Bemail:  " + c.getString(6)+ "\n"+
                        "OpenDay:  " + c.getString(7)+ "\n"+
                        "URL:  " + c.getString(8)+ "\n"+
                        "Location:  " + c.getString(9)+ "\n", Toast.LENGTH_LONG).show();
    }
    public void getCount(View v){
        db.open();
       long s= db.getShopsCount("s");
        Toast.makeText(getApplicationContext(),"count= "+s,Toast.LENGTH_LONG).show();
        db.close();
    }

}
