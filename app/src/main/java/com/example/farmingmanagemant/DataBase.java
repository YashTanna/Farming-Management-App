package com.example.farmingmanagemant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserData";
    private static final String TABLE_NAME = "users";
    private static final String TABLE_PHONE_NUMBER = "phone_number";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_DESCRIPTION = "description";
    private static final String TABLE_USERNAME = "name";
    private static final String TABLE_ADDRESS = "address";
    private static final String TABLE_PINCODE = "pincode";
    private static final String TABLE_CITY = "city";
    private static final String TABLE_STATE = "state";
    private static final String TABLE_COUNTRY = "country";
    private static final String TABLE_WEBSITE = "website";
    private static final String TABLE_EMAIL = "email";
    private static final String TABLE_FACEBOOK = "facebook";
    private static final String TABLE_INSTAGRAM = "instagram";
    private Context context;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 3);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + TABLE_PHONE_NUMBER + " TEXT PRIMARY KEY, "
                        + TABLE_CATEGORY + " TEXT, "
                        + TABLE_DESCRIPTION + " TEXT, "
                        + TABLE_USERNAME + " TEXT, "
                        + TABLE_ADDRESS + " TEXT, "
                        + TABLE_PINCODE + " TEXT, "
                        + TABLE_CITY + " TEXT, "
                        + TABLE_STATE + " TEXT, "
                        + TABLE_COUNTRY + " TEXT, "
                        + TABLE_WEBSITE + " TEXT, "
                        + TABLE_EMAIL + " TEXT, "
                        + TABLE_FACEBOOK + " TEXT, "
                        + TABLE_INSTAGRAM + " TEXT"
                        + ");";

            db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void addInfo(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_PHONE_NUMBER, number);

        // Insert the new phone number record
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addInfo(String phoneNumber, String category, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_CATEGORY, category);
        values.put(TABLE_DESCRIPTION, description);

        // Update existing record or insert if not exists
        int rowsAffected = db.update(TABLE_NAME, values, TABLE_PHONE_NUMBER + " = ?", new String[]{phoneNumber});
        if (rowsAffected == 0) {
            // Record doesn't exist, so insert new one
            values.put(TABLE_PHONE_NUMBER, phoneNumber); // Ensure phone number is included
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public void addInfo(String phoneNumber, String name, String address, String pincode,
                        String city, String state, String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_USERNAME, name);
        values.put(TABLE_ADDRESS, address);
        values.put(TABLE_PINCODE, pincode);
        values.put(TABLE_CITY, city);
        values.put(TABLE_STATE, state);
        values.put(TABLE_COUNTRY, country);

        // Update existing record or insert if not exists
        int rowsAffected = db.update(TABLE_NAME, values, TABLE_PHONE_NUMBER + " = ?", new String[]{phoneNumber});
        if (rowsAffected == 0) {
            // Record doesn't exist, so insert new one
            values.put(TABLE_PHONE_NUMBER, phoneNumber); // Ensure phone number is included
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public void addInfo(String phoneNumber, String website, String email,
                        String facebook, String instagram) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_WEBSITE, website);
        values.put(TABLE_EMAIL, email);
        values.put(TABLE_FACEBOOK, facebook);
        values.put(TABLE_INSTAGRAM, instagram);

        // Update existing record or insert if not exists
        int rowsAffected = db.update(TABLE_NAME, values, TABLE_PHONE_NUMBER + " = ?", new String[]{phoneNumber});
        if (rowsAffected == 0) {
            // Record doesn't exist, so insert new one
            values.put(TABLE_PHONE_NUMBER, phoneNumber); // Ensure phone number is included
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }


    public boolean isNumberExist(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT 1 FROM " + TABLE_NAME + " WHERE " + TABLE_PHONE_NUMBER + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{phoneNumber});
        boolean exist = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exist;
    }

    public String getCategory(String numbre){
        SQLiteDatabase db = this.getReadableDatabase();
        String category;
        String query = "SELECT "+TABLE_CATEGORY+" FROM "+TABLE_NAME+" WHERE "+TABLE_PHONE_NUMBER+" = ?";
        Cursor cursor = db.rawQuery(query,new String[]{numbre});
        if (cursor != null && cursor.moveToFirst()) {
            category = cursor.getString(0);
            cursor.close();
        }else {
            category = "";
        }

        db.close();
        return category;
    }

    @SuppressLint("Range")
    public MyDataType getNameAndEmail(String number){
        MyDataType data = new MyDataType(number);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + TABLE_EMAIL + ", " + TABLE_USERNAME + " from " + TABLE_NAME + " where " + TABLE_PHONE_NUMBER + " = ?";
        Cursor cursor =  db.rawQuery(query,new String[]{number});
        if (cursor.moveToFirst()) {
            data.name = cursor.getString(cursor.getColumnIndex(TABLE_USERNAME));
            data.email = cursor.getString(cursor.getColumnIndex(TABLE_EMAIL));
        }else{
            data.name = null;
            data.email = null;
        }
        cursor.close();
        db.close();
        return data;
    }

    public MyDataType getInfo(String number){
        MyDataType data = new MyDataType(number);
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from "+TABLE_NAME+" where "+TABLE_PHONE_NUMBER+" = ?";
        Cursor cursor = db.rawQuery(query,new String[]{number});

        if(cursor.getCount() > 0 && cursor.moveToFirst()){
            data.name = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_USERNAME));
            data.email = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_EMAIL));
            data.phonenumber = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_PHONE_NUMBER));
            data.address = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_ADDRESS));
            data.city = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_CITY));
            data.state = cursor.getString(cursor.getColumnIndexOrThrow(TABLE_STATE));
        }

        return data;
    }

}
