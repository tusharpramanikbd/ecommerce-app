package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.R;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnRegister, btn_login;
    private AppCompatTextView tvSkip;
    private LinearLayout layout_invalid_sign;
    private AppCompatEditText etEmailOrUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initializeUI();


        //Button events.......................
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class) );
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ProductCategoryActivity.class) );
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etEmailOrUserName.getText().toString();
                String password = etPassword.getText().toString();

                if(userName.equals("Tushar") && password.equals("12345")){
                    startActivity(new Intent(LoginActivity.this, ProductCategoryActivity.class) );
                }
                else {
                    etEmailOrUserName.setText("");
                    etPassword.setText("");
                    layout_invalid_sign.setVisibility(View.VISIBLE);
                }
            }
        });

        etEmailOrUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    layout_invalid_sign.setVisibility(View.GONE);
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    layout_invalid_sign.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initializeUI() {
        //Initializing the components...............
        btnRegister = findViewById(R.id.btnRegister);
        tvSkip = findViewById(R.id.tvSkip);
        layout_invalid_sign =findViewById(R.id.layout_invalid_sign);
        btn_login =findViewById(R.id.btn_login);
        etEmailOrUserName =findViewById(R.id.etEmailOrUserName);
        etPassword =findViewById(R.id.etPassword);

        //hide soft keyboard on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
