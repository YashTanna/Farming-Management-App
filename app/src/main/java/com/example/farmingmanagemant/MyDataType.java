package com.example.farmingmanagemant;

import android.util.Log;

public class MyDataType {

    public MyDataType(String number,String name,String email){

        this.phonenumber = number;
        this.name = name;
        this.email = email;
    }



    public MyDataType(String number){
        this.phonenumber = number;
    }

    String phonenumber;
    String category;
    String discription;
    String name;
    String address;
    String pincode;
    String city;
    String state;
    String country;
    String website;
    String email;
    String facebook;
    String instagram;

}
