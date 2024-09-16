//package com.example.farmingmanagemant;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class SplashScreen extends AppCompatActivity {
//
//    ImageView img;
//    Intent intent;
//    TextView text;
//    SharedPreferences sp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//
//        img = findViewById(R.id.poster);
//        text = findViewById(R.id.text);
//        sp = getSharedPreferences("login",MODE_PRIVATE);
//
//        if(sp.contains("phonenumber")){
//            startActivity(new Intent(this,HomePage.class));
//        }
//
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
//        img.startAnimation(animation);
//        text.startAnimation(animation);
//
//        intent = new Intent(this,FirstPage.class);
//
//        new Handler().postDelayed(()->{
//            startActivity(intent);
//            finish();
//        },1000);
//
//    }
//}