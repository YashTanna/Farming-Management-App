package com.example.farmingmanagemant;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    TextView fname,lname,email,number,pass,confpass;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signup = findViewById(R.id.btn_submit);
        pass = findViewById(R.id.et_pass);
        confpass = findViewById(R.id.et_confpass);

        btn_signup.setOnClickListener(v->{
            if(!pass.getText().toString().equals(confpass.getText().toString())){
                confpass.setError("Password does not match");
                Toast.makeText(this,"Password and Confirm Password should be same",Toast.LENGTH_SHORT).show();
            }
        });

    }
}