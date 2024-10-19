package com.example.farmingmanagemant;

import com.example.farmingmanagemant.fragment.*;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.farmingmanagemant.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView navigationView;
    private boolean isFirst = true;
    BottomNavigationView bnv;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationdrawer;
    SharedPreferences sp;
    DataBase db;
    String phonenumber;
    TextView profileName,emailId;
//    ImageView profilePic;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        navigationView = findViewById(R.id.navigation);
        bnv = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationdrawer = findViewById(R.id.navigationDrawer);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        db = new DataBase(this);
        phonenumber = sp.getString("number",null);
        View header = navigationdrawer.getHeaderView(0);

        if(phonenumber == null){
            Intent inten2 = new Intent(this,Login.class);
            Toast.makeText(this,"Plase Login Again",Toast.LENGTH_SHORT).show();
            startActivity(inten2);
        }
        profileName = header.findViewById(R.id.profileName);
        emailId = header.findViewById(R.id.emailId);

        MyDataType data = new MyDataType(phonenumber);
        data = db.getNameAndEmail(phonenumber);

        profileName.setText(data.name);
        emailId.setText(data.email);


        //Set Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloswDrawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationdrawer.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.logout){
                generateDialog();
            }else{
                loadFrag(new HomeFragment());
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        bnv.setOnItemSelectedListener(item -> {
            loadFrag(new HomeFragment());
            return true;
        });

        loadFrag(new HomeFragment());

        navigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.home){
                loadFrag(new HomeFragment());
            }else if(id == R.id.loan){
                loadFrag(new LoanFragment());
            }else{
                loadFrag(new VideoFragment());
            }
            return true;
        });


    }

    public void loadFrag(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(isFirst){
            ft.add(R.id.container,fragment);
            isFirst = false;
        }else{
            ft.replace(R.id.container,fragment);
        }
        ft.commit();
    }

    public void onBackPressed() {
        // Check if the drawer is open
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void generateDialog(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Logout?");
        alertDialog.setMessage("Are you sure you want to logout?");
        alertDialog.setIcon(R.drawable.logout);
        alertDialog.setPositiveButton("Logout",(dialog,i)->{

            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.commit();
            startActivity(new Intent(this,FirstPage.class));
        });

        alertDialog.setNegativeButton("Cancle",(dialog,i)->{

        });
        alertDialog.show();
    }
}