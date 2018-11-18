package com.nitto.tushar.nrrii.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.nitto.tushar.nrrii.R;

public class CongratulationScreenActivity extends AppCompatActivity {

    private AppCompatButton btnSignupNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation_screen_new);

        btnSignupNow = findViewById(R.id.btnSignupNow);
        btnSignupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CongratulationScreenActivity.this, SignUpActivity.class) );

            }
        });
    }
}
