package com.example.farmingmanagemant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "ID";
    private static final String COL_PHONE = "Phone";
    private Context context;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_PHONE + " TEXT UNIQUE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to check if a phone number already exists
    public boolean isPhoneNumberExists(String phoneNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_PHONE + "=?", new String[]{phoneNumber});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Method to insert a phone number into the database
    public boolean insertPhoneNumber(String phoneNumber) {
        if (isPhoneNumberExists(phoneNumber)) {
            Toast.makeText(context, "Number already exists", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_PHONE, phoneNumber);
            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1; // Return true if insertion was successful
        }
    }


}
