package com.example.farmingmanagemant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FarmingApp";
    private static final String TABLE_INFO = "userdata";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "first_name";
    private static final String KEY_LASTNAME = "last_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NO = "phone_number";
    private static final String KEY_PASSWORD = "password";


    MyDBHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_INFO+"( "+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_FIRSTNAME+
                " TEXT NOT NULL, "+KEY_LASTNAME+" TEXT NOT NULL, "
                +KEY_EMAIL+" TEXT NOT NULL, "+KEY_PHONE_NO+" TEXT NOT NULL, "+KEY_PASSWORD+" TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_INFO);
        onCreate(db);

    }

    public void addInfo(MyDataType mdt){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_FIRSTNAME,mdt.fname);
        values.put(KEY_LASTNAME,mdt.lname);
        values.put(KEY_EMAIL,mdt.email);
        values.put(KEY_PHONE_NO,mdt.phone);
        values.put(KEY_PASSWORD,mdt.pass);

        db.insert(TABLE_INFO,null,values);
        db.close();
    }

    public ArrayList<MyDataType> getInfo(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_INFO,null);

        ArrayList<MyDataType> list = new ArrayList<>();

        while(cursor.moveToNext()){
            MyDataType mdt = new MyDataType();
            mdt.id = cursor.getInt(0);
            mdt.fname = cursor.getString(1);
            mdt.lname = cursor.getString(2);
            mdt.email = cursor.getString(3);
            mdt.phone = cursor.getString(4);
            mdt.pass = cursor.getString(5);
            list.add(mdt);
        }
        cursor.close();
        db.close();
        return list;
    }

    boolean userAuthentication(MyDataType mdt){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+TABLE_INFO+" WHERE "+KEY_EMAIL+" = ? AND "+KEY_PASSWORD+" = ?";

        Cursor cursor = db.rawQuery(query,new String[]{mdt.email,mdt.pass});
        boolean exist = false;
        if(cursor != null && cursor.getCount() > 0){
            exist = true;
        }
        cursor.close();
        db.close();
        return exist;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_INFO);
        db.close();
    }

}
