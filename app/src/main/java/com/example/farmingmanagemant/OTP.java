package com.example.farmingmanagemant;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OTP extends AppCompatActivity {

    private EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    private String generatedOtp;
    Button generate_otp_button;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpDigit1 = findViewById(R.id.otp_digit_1);
        otpDigit2 = findViewById(R.id.otp_digit_2);
        otpDigit3 = findViewById(R.id.otp_digit_3);
        otpDigit4 = findViewById(R.id.otp_digit_4);
        generate_otp_button = findViewById(R.id.generate_otp_button);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        // Retrieve the generated OTP from the previous activity
        generatedOtp = getIntent().getStringExtra("generatedOtp");
        String phoneNumber = getIntent().getStringExtra("phonenumber");

        // Set the click listener for the button
        generate_otp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Concatenate the digits entered in the EditText fields
                String enteredOtp = otpDigit1.getText().toString() +
                        otpDigit2.getText().toString() +
                        otpDigit3.getText().toString() +
                        otpDigit4.getText().toString();

                // Validate the OTP
                if (enteredOtp.equals(generatedOtp)) {
                    // If OTP is valid, navigate to the next activity
                    Intent intent = new Intent(OTP.this, HomePage.class);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("phonenumber",phoneNumber);
                    edit.commit();
                    startActivity(intent);
                    finish();  // Close the current activity
                } else {
                    // If OTP is invalid, show a toast message
                    Toast.makeText(OTP.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Setup automatic movement between EditText fields
        setupOtpInputMovement();

        // Validate the OTP when the user finishes entering the 4th digit
        otpDigit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    // Handle automatic movement between OTP input fields
    private void setupOtpInputMovement() {
        otpDigit1.addTextChangedListener(new OtpTextWatcher(otpDigit1, otpDigit2));
        otpDigit2.addTextChangedListener(new OtpTextWatcher(otpDigit2, otpDigit3));
        otpDigit3.addTextChangedListener(new OtpTextWatcher(otpDigit3, otpDigit4));
    }



    // Custom TextWatcher class to handle focus change
    private class OtpTextWatcher implements TextWatcher {

        private EditText currentView, nextView;

        public OtpTextWatcher(EditText currentView, EditText nextView) {
            this.currentView = currentView;
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 1 && nextView != null) {
                nextView.requestFocus();  // Move to the next EditText once a digit is entered
            }
        }
    }
}

