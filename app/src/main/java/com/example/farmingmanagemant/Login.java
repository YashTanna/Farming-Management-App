package com.example.farmingmanagemant;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Login extends AppCompatActivity {

//    SharedPreferences sp;
    private static final String CHANNEL_ID = "OTP_NOTIFICATION_CHANNEL";
    private EditText phoneNumberField;
    private DataBase logindatabase;
    private String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumberField = findViewById(R.id.phone_number);
        logindatabase = new DataBase(this);


        // Create the notification channel
        createNotificationChannel();

        findViewById(R.id.send_otp_button).setOnClickListener(v -> {
            String phoneNumber = phoneNumberField.getText().toString();
            if (phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) {
                phoneNumberField.setBackgroundResource(R.drawable.error_border);
                Toast.makeText(Login.this, "Check your phone number", Toast.LENGTH_SHORT).show();
            } else {
                phoneNumberField.setBackgroundResource(R.drawable.normal_border);
                if (logindatabase.insertPhoneNumber(phoneNumber)) {
                    generatedOtp = generateOtp();  // Generate OTP
                    sendOtpNotification(generatedOtp);  // Send OTP via notification

                    // Proceed to OtpActivity and pass the OTP
                    Intent intent = new Intent(Login.this, OTP.class);
                    intent.putExtra("generatedOtp", generatedOtp);
                    intent.putExtra("phonenumber",phoneNumberField.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    // Generate random 4-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // 4-digit OTP
        return String.valueOf(otp);
    }

    // Send OTP via notification
    private void sendOtpNotification(String otp) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.india)  // Use your app icon
                .setContentTitle("Your OTP Code")
                .setContentText("Your OTP is: " + otp)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);  // Automatically removes notification when clicked

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    // Create notification channel (required for Android 8.0 and above)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "OTP Notifications";
            String description = "Channel for OTP notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}