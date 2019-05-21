package com.app.arkan.disclostore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_USER_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String KEY_PASSWORD = "password";
    static final String KEY_PHONE= "phone";
    static final String KEY_ROLE = "role";

    static final String KEY_ROWID2 = "_id";
    static final String KEY_CATEGORY = "category";
    static final String KEY_IMAGE = "image";
    static final String KEY_ABOUT = "about";
    static final String KEY_OPEN_DAY= "openday";
    static final String KEY_FAX = "fax";
    static final String KEY_PHONE_B= "bphone";
    static final String KEY_EMAIL_B= "bemail";
    static final String KEY_URL = "url";
    static final String KEY_LOCATION = "location";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "DiscloStoreDB";
    static final String DATABASE_USER_TABLE = "users";
    static final String DATABASE_BUSINESS_TABLE = "business";

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE_USERS =
            "create table users (_id integer primary key autoincrement, "
                    + "name text not null, email text not null, password text not null, phone text not null, role text not null);";
    static final String DATABASE_CREATE_BUSINESS =
            "create table business (_id integer primary key autoincrement, "
                    + "category text not null, image text not null, " +
                    "about text not null, openday text not null, fax text not null, bphone text not null, url text not null," +
                    "location text not null" +
                    ",bemail text not null, password text not null, phone text not null, role text not null);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE_USERS);
                db.execSQL(DATABASE_CREATE_BUSINESS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            //Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                  //  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS business");
            onCreate(db);
        }
    }


    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    public void close()
    {
        DBHelper.close();
    }

    public long insertBusiness(String category, String image, String openday, String fax, String bphone, String bemail, String url, String location)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY, category);
        initialValues.put(KEY_EMAIL_B, bemail);
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_PHONE_B, bphone);
        initialValues.put(KEY_URL, url);
        initialValues.put(KEY_OPEN_DAY, openday);
        initialValues.put(KEY_FAX, fax);
        initialValues.put(KEY_LOCATION, location);
        return db.insert(DATABASE_USER_TABLE, null, initialValues);
    }
    public long insertUser(String name, String email, String password, String phone, String role)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USER_NAME, name);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_ROLE, role);
        return db.insert(DATABASE_USER_TABLE, null, initialValues);
    }


    public boolean deleteUser(long rowId)
    {
        return db.delete(DATABASE_USER_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean deleteBusiness(long rowId)
    {
        return db.delete(DATABASE_BUSINESS_TABLE, KEY_ROWID2 + "=" + rowId, null) > 0;
    }
    public Cursor getAllUsers()
    {
        return db.query(DATABASE_USER_TABLE, new String[] {KEY_ROWID, KEY_EMAIL,
        KEY_PASSWORD, KEY_USER_NAME, KEY_PHONE, KEY_ROLE}, null, null, null, null, null);
    }
    public Cursor getAllBusiness()
    {
        return db.query(DATABASE_BUSINESS_TABLE, new String[] {KEY_ROWID2,KEY_CATEGORY,KEY_IMAGE,KEY_ABOUT,
                KEY_OPEN_DAY, KEY_FAX, KEY_PHONE_B,KEY_EMAIL_B,
                KEY_URL,KEY_LOCATION}, null, null, null, null, null);
    }
    public Cursor getBusiness(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_USER_TABLE, new String[] {KEY_ROWID2,KEY_CATEGORY,KEY_IMAGE,KEY_ABOUT,
                                KEY_OPEN_DAY, KEY_FAX, KEY_PHONE_B,KEY_EMAIL_B,
                                KEY_URL,KEY_LOCATION}, KEY_ROWID2 + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getUser(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_USER_TABLE, new String[] {KEY_ROWID,
                                KEY_USER_NAME, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public boolean updateBusiness(String rowId, String category, String image, String openday, String fax, String bphone, String bemail, String url, String location)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY, category);
        initialValues.put(KEY_EMAIL_B, bemail);
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_PHONE_B, bphone);
        initialValues.put(KEY_URL, url);
        initialValues.put(KEY_OPEN_DAY, openday);
        initialValues.put(KEY_FAX, fax);
        initialValues.put(KEY_LOCATION, location);
        return db.update(DATABASE_BUSINESS_TABLE, initialValues, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateUser(long rowId, String name, String email, String password, String phone, String role)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_USER_NAME, name);
        args.put(KEY_EMAIL, email);
        args.put(KEY_PASSWORD, password);
        args.put(KEY_PHONE, phone);
        args.put(KEY_ROLE, role);
        return db.update(DATABASE_USER_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


}
