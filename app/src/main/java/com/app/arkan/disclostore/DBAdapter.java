package com.app.arkan.disclostore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DBAdapter {
    //ownerships table
    static final String DATABASE_OWNERSHIPS_TABLE = "ownerships";
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
    static final String KEY_SOTRE_NAME = "storename";
    static final String KEY_RATING = "rating";
    static final String KEY_RATING_COUNTER = "ratecounter";
    //users table
    static final String DATABASE_USER_TABLE = "users";
    static final String KEY_ROWID = "_id";
    static final String KEY_USER_NAME = "username";
    static final String KEY_EMAIL = "email";
    static final String KEY_PASSWORD = "password";
    static final String KEY_PHONE= "phone";
    static final String KEY_ROLE = "role";

    //ownership table
    static final String DATABASE_BUSINESS_TABLE = "ownership";


    //catagories
    static final String DATABASE_CATEGORY_TABLE = "categories";
    static final String KEY_ROWID3 = "_id";
    static final String KEY_CATEGORY_ID = "categoryid";

    //storeinfo
    static final String DATABASE_STORE_TABLE = "storeinfo";
    static final String KEY_ROWID4 = "_id";
    static final String KEY_CATEGORYs_ID = "categoryid";



    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "DiscloStoreDB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE_USERS =
            "create table users (_id integer primary key autoincrement, "
                    + "username text not null, email text not null, password text not null, phone text not null, role int not null);";


    static final String DATABASE_CREATE_OWNERSHIP =
            "create table ownership (_id integer primary key autoincrement, "
                    + "category integer not null, image BLOB NOT NULL, " +
                    "about text not null, fax text not null,bphone text not null, bemail text not null, " +
                    "openday text not null,  url text not null," +
                    "location text not null);";
    static final String DATABASE_CREATE_OWNERSHIPs =
            "create table ownerships (_id integer primary key autoincrement, "
                    + "category integer not null, image BLOB NOT NULL, " +
                    "about text not null, fax text not null,bphone text not null, bemail text not null, " +
                    "openday text not null,  url text not null," +
                    "location text not null, storename text not null, rating integer not null, ratecounter integer not null);";
    static final String DATABASE_CREATE_CATEGORIES =
            "create table categories (_id integer primary key autoincrement, "
                    + "categoryid integer not null);";

    static final String DATABASE_CREATE_STOREINFO =
            "create table storeinfo (_id integer primary key autoincrement, "
                    + "categoryid integer not null, storename text not null, rating integer not null, ratecounter integer not null);";

    //static final String ALTER_OWNERSHIP_ADD_FK = "ALTER TABLE ownership ADD FOREIGN KEY (category) REFERENCES categories(id)";
   // static final String ALTER_CATEGORIES_ADD_FK = "ALTER TABLE categories ADD FOREIGN KEY (categoryid) REFERENCES storeinfo(id)";

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
                db.execSQL(DATABASE_CREATE_OWNERSHIP);
                db.execSQL(DATABASE_CREATE_CATEGORIES);
                db.execSQL(DATABASE_CREATE_STOREINFO);
                db.execSQL(DATABASE_CREATE_OWNERSHIPs);

             //   db.execSQL(ALTER_CATEGORIES_ADD_FK);
             //   db.execSQL(ALTER_OWNERSHIP_ADD_FK);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS ownership");
            db.execSQL("DROP TABLE IF EXISTS categories");
            db.execSQL("DROP TABLE IF EXISTS storeinfo");
            db.execSQL("DROP TABLE IF EXISTS ownerships");

//            if (newVersion > oldVersion) {
//                db.execSQL("ALTER TABLE storeinfo ADD COLUMN ratecounter INTEGER DEFAULT 0");
//            }
          //  db.execSQL(ALTER_CATEGORIES_ADD_FK);
         //   db.execSQL(ALTER_OWNERSHIP_ADD_FK);
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

    public void updateTable(){
        db.execSQL(DATABASE_CREATE_OWNERSHIPs);
       // db.execSQL("ALTER TABLE foo ADD COLUMN new_column INTEGER DEFAULT 0");
        //db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_STORE_TABLE + "'");
       // db.execSQL(DATABASE_CREATE_STOREINFO);
    }

    public long insertOwnership(int category, String about, byte[] image, String openday, String fax, String bphone, String bemail, String url, String location)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY, category);
        initialValues.put(KEY_ABOUT, about);
        initialValues.put(KEY_EMAIL_B, bemail);
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_PHONE_B, bphone);
        initialValues.put(KEY_URL, url);
        initialValues.put(KEY_OPEN_DAY, openday);
        initialValues.put(KEY_FAX, fax);
        initialValues.put(KEY_LOCATION, location);
        return db.insert(DATABASE_BUSINESS_TABLE, null, initialValues);
    }
    public long insertOwnerships(int category, String about, byte[] image, String openday, String fax, String bphone, String bemail,
                                 String url, String location, String storename, int rating, int counter)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY, category);
        initialValues.put(KEY_ABOUT, about);
        initialValues.put(KEY_EMAIL_B, bemail);
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_PHONE_B, bphone);
        initialValues.put(KEY_URL, url);
        initialValues.put(KEY_OPEN_DAY, openday);
        initialValues.put(KEY_FAX, fax);
        initialValues.put(KEY_LOCATION, location);
        initialValues.put(KEY_SOTRE_NAME, storename);
        initialValues.put(KEY_RATING, rating);
        initialValues.put(KEY_RATING_COUNTER, counter);
        return db.insert(DATABASE_OWNERSHIPS_TABLE, null, initialValues);
    }
    public long insertUser(String name, String email, String password, String phone, int role)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USER_NAME, name);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_ROLE, role);
        return db.insert(DATABASE_USER_TABLE, null, initialValues);
    }
    public long insertCategory(int category)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY_ID, category);
        return db.insert(DATABASE_CATEGORY_TABLE, null, initialValues);
    }
    public long insertStoreinfo(int category, String storename, int rating, int counter)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORYs_ID, category);
        initialValues.put(KEY_SOTRE_NAME, storename);
        initialValues.put(KEY_RATING, rating);
        initialValues.put(KEY_RATING_COUNTER, counter);
        return db.insert(DATABASE_STORE_TABLE, null, initialValues);
    }

    public boolean deleteUser(long rowId)
    {
        return db.delete(DATABASE_USER_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean deleteOwnership(long rowId)
    {
        return db.delete(DATABASE_BUSINESS_TABLE, KEY_ROWID2 + "=" + rowId, null) > 0;
    }
    public Cursor getAllUsers()
    {
        return db.query(DATABASE_USER_TABLE, new String[] {KEY_ROWID, KEY_USER_NAME, KEY_EMAIL,
        KEY_PASSWORD, KEY_PHONE, KEY_ROLE}, null, null, null, null, null);
    }
    public Cursor getAllCategories()
    {
        return db.query(DATABASE_CATEGORY_TABLE, new String[] {KEY_ROWID3, KEY_CATEGORY_ID}, null, null, null, null, null);
    }
    public Cursor getAllStore()
    {
        return db.query(DATABASE_STORE_TABLE, new String[] {KEY_ROWID4, KEY_CATEGORYs_ID, KEY_SOTRE_NAME, KEY_RATING, KEY_RATING_COUNTER}, null, null, null, null, null);
    }
    public Cursor getAllOwnerships()
    {
        return db.query(DATABASE_BUSINESS_TABLE, new String[] {KEY_ROWID2,KEY_CATEGORY,KEY_IMAGE,KEY_ABOUT,
                 KEY_FAX, KEY_PHONE_B,KEY_EMAIL_B,KEY_OPEN_DAY,
                KEY_URL,KEY_LOCATION,KEY_SOTRE_NAME, KEY_RATING, KEY_RATING_COUNTER
        }, null, null, null, null, null);
    }
    public Cursor getAllOwnership()
    {
        return db.query(DATABASE_BUSINESS_TABLE, new String[] {KEY_ROWID2,KEY_CATEGORY,KEY_IMAGE,KEY_ABOUT,
                KEY_FAX, KEY_PHONE_B,KEY_EMAIL_B,KEY_OPEN_DAY,
                KEY_URL,KEY_LOCATION}, null, null, null, null, null);
    }
    public Cursor getOwnership(long rowId) throws SQLException
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
    public Cursor CheckUser(String email) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_USER_TABLE, new String[] {KEY_EMAIL,KEY_PASSWORD,KEY_ROLE},
                        KEY_EMAIL + " = '"+ email+"' AND "+KEY_PASSWORD , null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor CheckUserLogin(String email, String password) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_USER_TABLE, new String[] {KEY_EMAIL,KEY_PASSWORD,KEY_ROLE},
                        KEY_EMAIL + " = '"+ email+"' AND "+KEY_PASSWORD + " = '" + password+"'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor CheckUserRegister(String email) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_USER_TABLE, new String[] {KEY_EMAIL},
                        KEY_EMAIL + " = '"+ email+"'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public boolean updateOwnership(String rowId, int category, byte[] image, String openday, String fax, String bphone,
                                   String bemail, String url, String location, String storename, int rating, int counter)
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
        initialValues.put(KEY_SOTRE_NAME, storename);
        initialValues.put(KEY_RATING, rating);
        initialValues.put(KEY_RATING_COUNTER, counter);
        return db.update(DATABASE_BUSINESS_TABLE, initialValues, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean updateOwnerships(int rowId, int category, byte[] image, String about, String openday, String fax, String bphone,
                                   String bemail, String url, String location, String storename)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CATEGORY, category);
        initialValues.put(KEY_ABOUT,about);
        initialValues.put(KEY_EMAIL_B, bemail);
        initialValues.put(KEY_IMAGE, image);
        initialValues.put(KEY_PHONE_B, bphone);
        initialValues.put(KEY_URL, url);
        initialValues.put(KEY_OPEN_DAY, openday);
        initialValues.put(KEY_FAX, fax);
        initialValues.put(KEY_LOCATION, location);
        initialValues.put(KEY_SOTRE_NAME, storename);
        return db.update(DATABASE_OWNERSHIPS_TABLE, initialValues, KEY_ROWID2 + "=" + rowId, null) > 0;
    }
    public boolean updateUser(long rowId, String name, String email, String password, String phone, int role)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_USER_NAME, name);
        args.put(KEY_EMAIL, email);
        args.put(KEY_PASSWORD, password);
        args.put(KEY_PHONE, phone);
        args.put(KEY_ROLE, role);
        return db.update(DATABASE_USER_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public boolean updateRating(String storename,int rating, int counter) {
        ContentValues args = new ContentValues();
        args.put(KEY_RATING, rating);
        args.put(KEY_RATING_COUNTER, counter);
        return db.update(DATABASE_OWNERSHIPS_TABLE, args, KEY_SOTRE_NAME + " = '"+ storename+"'", null) > 0;
    }

    public long getShopsCount(int categoryid){
        String countQuery = "SELECT  * FROM " + DATABASE_OWNERSHIPS_TABLE + " where " + KEY_CATEGORY + " = " + categoryid  ;
         db = DBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getShopRating(String shopname){
        int rate = 0;
        db = DBHelper.getReadableDatabase();
        Cursor crs = db.rawQuery("SELECT rating FROM " + DATABASE_OWNERSHIPS_TABLE + " where " + KEY_SOTRE_NAME + " = '" + shopname +"'", null);
        crs.moveToFirst();
        while (crs.isAfterLast() == false){
          rate = Integer.valueOf(crs.getString(0));
            //check devide by zero
            crs.moveToNext();
        }
        return rate;
    }
    public Cursor getListShopRow(int categoryid){
        Cursor mCursor =
                db.query(true, DATABASE_OWNERSHIPS_TABLE, new String[] {KEY_IMAGE, KEY_SOTRE_NAME, KEY_RATING},
                        KEY_CATEGORY + " = "+ categoryid, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getShop(String Storename){
        Cursor mCursor =
                db.query(true, DATABASE_OWNERSHIPS_TABLE, new String[] {KEY_IMAGE, KEY_SOTRE_NAME,KEY_ABOUT, KEY_OPEN_DAY,KEY_LOCATION,KEY_PHONE_B,KEY_FAX,KEY_EMAIL_B,KEY_URL,KEY_ROWID4,KEY_CATEGORY},
                        KEY_SOTRE_NAME + " = "+"'"+ Storename+"'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //while
    public Bitmap get(String storename) {
        db = DBHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select image from ownerships where " + KEY_SOTRE_NAME + " = '" + storename +"'", null);
        c.moveToFirst();
        while (c.isAfterLast() == false)
        {
            byte[] image = c.getBlob(0);
            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
            c.moveToNext();
            return bmp;
        }
        return null;
    }
    public String getShopName(String email) {
        db = DBHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select storename from ownerships where " + KEY_EMAIL_B + " = '" + email +"'" , null);
        if(c.moveToNext())
        {
           String shopname = c.getString(0);
            return shopname;

        }
        return null;
    }
    public int getShopCounter(String shopname) {
        db = DBHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  ratecounter FROM " + DATABASE_OWNERSHIPS_TABLE + " where " + KEY_SOTRE_NAME + " = '" + shopname +"'", null);
        if(c.moveToNext())
        {
            int shopnames = Integer.valueOf(c.getString(0));
            return shopnames;

        }
        return 0;
    }
    public String getEmail(String email) {
        db = DBHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  password FROM " + DATABASE_USER_TABLE + " where " + KEY_EMAIL + " = '" + email +"'", null);
        if(c.moveToNext())
        {
            String password = (c.getString(0));
            return password;

        }
        return null;
    }


}
