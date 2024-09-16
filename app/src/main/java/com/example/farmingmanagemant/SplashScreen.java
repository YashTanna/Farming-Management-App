package com.example.farmingmanagemant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView splashImage = findViewById(R.id.poster);

        // Load the zoom-in animation
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        splashImage.startAnimation(zoomIn);

        // Handler to delay transition to the next activity after 3 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this,FirstPage.class);
            startActivity(intent);
            finish(); // Close splash screen activity
        }, 3000); // 3 seconds
    }
}
