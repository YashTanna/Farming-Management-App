package com.example.farmingmanagemant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

        //Set Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloswDrawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationdrawer.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.logout){
                generateDialog();
            }else{
                loadFrag(new Home());
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        bnv.setOnItemSelectedListener(item -> {
            loadFrag(new Home());
            return true;
        });

        loadFrag(new Home());

        navigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.home){
                loadFrag(new Home());
            }else if(id == R.id.loan){
                loadFrag(new Home());
            }else{
                loadFrag(new Home());
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

            db.deleteByNumber(sp.getString("phonenumber",null));
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