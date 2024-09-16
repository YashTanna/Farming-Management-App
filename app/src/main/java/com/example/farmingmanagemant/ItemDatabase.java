package com.example.farmingmanagemant;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "item_storage_database";
    private static final String TABLE_NAME = "items";
    private static final String TABLE_PHONE_NUMBER = "phonenumber";
    private static final String TABLE_FARMER_NAME = "farmer_name";
    private static final String TABLE_DESCRIPTION = "description";
    private static final String TABLE_ITEM_TYPE = "item_type";
    private static final String TABLE_PRICE = "price";
    private static final String TABLE_LOCATION = "location";
    private static final String TABLE_TIME = "time";

    public ItemDatabase(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String createTable = "CREATE TABLE "+TABLE_NAME+" ( " +
                TABLE_PHONE_NUMBER+" TEXT PRIMARY KEY, "+
                TABLE_FARMER_NAME+" TEXT, "+
                TABLE_DESCRIPTION+" TEXT, "+
                TABLE_ITEM_TYPE+" TEXT, "+
                TABLE_PRICE+" TEXT, "+
                TABLE_LOCATION+" TEXT, "+
                TABLE_TIME+" TEXT );";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i < i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public void addItemToDatabase(ItemModule data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(TABLE_PHONE_NUMBER, data.number); // Primary key
        content.put(TABLE_FARMER_NAME, data.name);    // Farmer's name
        content.put(TABLE_DESCRIPTION, "");           // Add this field, as it's in the schema
        content.put(TABLE_ITEM_TYPE, data.type);      // Item type
        content.put(TABLE_PRICE, data.price);         // Price
        content.put(TABLE_LOCATION, data.location);   // Location (you missed this in your code)
        content.put(TABLE_TIME, data.time);           // Time

        // Insert the row into the database
        db.insert(TABLE_NAME, null, content);
        db.close();
    }


    @SuppressLint("Range")
    public ArrayList<ItemModule> getItemFromDatabase() {
        ArrayList<ItemModule> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_ITEM_TYPE + " = 'GARLIC'", null);

        Log.d("GarlicFragment", "Rows fetched: " + cursor.getCount());

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                String number = cursor.getString(cursor.getColumnIndex(TABLE_PHONE_NUMBER));
                String name = cursor.getString(cursor.getColumnIndex(TABLE_FARMER_NAME));
                String type = cursor.getString(cursor.getColumnIndex(TABLE_ITEM_TYPE));
                String price = cursor.getString(cursor.getColumnIndex(TABLE_PRICE));
                String location = cursor.getString(cursor.getColumnIndex(TABLE_LOCATION));
                String time = cursor.getString(cursor.getColumnIndex(TABLE_TIME));

                list.add(new ItemModule(number, name, type, price, location, time));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

}
