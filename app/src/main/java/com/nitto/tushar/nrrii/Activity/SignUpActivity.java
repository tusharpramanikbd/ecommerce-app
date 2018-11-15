package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nitto.tushar.nrrii.R;

public class SignUpActivity extends AppCompatActivity {

    private AppCompatEditText etName, etPhoneNumber, etEmailAddress, etPassword, etRetypePassword;
    private AppCompatButton btnSignUp;
    private LinearLayout layout_invalid_sign;
    private AppCompatTextView tvInvalidSignText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeUI();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhoneNumber.getText().toString();
                String email = etEmailAddress.getText().toString();
                String password = etPassword.getText().toString();
                String retypePassword = etRetypePassword.getText().toString();

                if(name.equals("") || phone.equals("") || email.equals("") || password.equals("") || retypePassword.equals("")) {
                    layout_invalid_sign.setVisibility(View.VISIBLE);
                    tvInvalidSignText.setText("  Invalid information");
                    etName.setText("");
                    etPhoneNumber.setText("");
                    etEmailAddress.setText("");
                    etPassword.setText("");
                    etRetypePassword.setText("");
                }
                else if(!password.equals(retypePassword)) {
                    layout_invalid_sign.setVisibility(View.VISIBLE);
                    tvInvalidSignText.setText("  Password does not match");
                    etPassword.setText("");
                    etRetypePassword.setText("");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    layout_invalid_sign.setVisibility(View.VISIBLE);
                    tvInvalidSignText.setText("  Invalid email address");
                    etEmailAddress.setText("");
                }
                else if(!isPhoneNumber(phone)){
                    layout_invalid_sign.setVisibility(View.VISIBLE);
                    tvInvalidSignText.setText("  Invalid phone number");
                    etPhoneNumber.setText("");
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class) );
                }
            }
        });

    }

    private void initializeUI() {
        etName = findViewById(R.id.etName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        etRetypePassword = findViewById(R.id.etRetypePassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        layout_invalid_sign =findViewById(R.id.layout_invalid_sign);
        tvInvalidSignText =findViewById(R.id.tvInvalidSignText);

        //hide soft keyboard on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private boolean isPhoneNumber(String s) {
        String pattern1 = "^01\\d{9}";
        String pattern2 = "^8801\\d{9}";

        if (s.matches(pattern1)|| s.matches(pattern2)) {
            return true;
        }
        return false;
    }
}
