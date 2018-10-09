package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.nitto.tushar.nrrii.R;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnRegister;
    private AppCompatTextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeUI();

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
    }

    private void initializeUI() {
        btnRegister = findViewById(R.id.btnRegister);
        tvSkip = findViewById(R.id.tvSkip);
    }
}
