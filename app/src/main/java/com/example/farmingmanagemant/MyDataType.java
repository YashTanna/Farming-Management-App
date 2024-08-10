package com.example.farmingmanagemant;

import android.util.Log;

public class MyDataType {
    int id;
    String fname;
    String lname;
    String email;
    String phone;
    String pass;

    public void display(){
        Log.d("UserInfo","Id : "+id+", fname : "+fname+", lname : "+ lname+", email : "+email+", phone : "+phone+", pass : "+pass);
    }
}
