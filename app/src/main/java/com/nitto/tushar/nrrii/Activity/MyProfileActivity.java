package com.nitto.tushar.nrrii.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.nitto.tushar.nrrii.R;

public class MyProfileActivity extends AppCompatActivity {

    private AppCompatTextView tvEditProfile, tvFullName, tvAge, tvGender, tvContactNumber, tvEmail, tvAddress;
    private AppCompatEditText etFullName, etAge, etGender, etContactNumber, etEmail, etAddress;
    private AppCompatButton btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        tvEditProfile = findViewById(R.id.tvEditProfile);
        tvFullName = findViewById(R.id.tvFullName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        tvContactNumber = findViewById(R.id.tvContactNumber);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        etFullName = findViewById(R.id.etFullName);
        etAge = findViewById(R.id.etAge);
        etGender = findViewById(R.id.etGender);
        etContactNumber = findViewById(R.id.etContactNumber);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        btnDone = findViewById(R.id.btnDone);


        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEditProfile.setVisibility(View.INVISIBLE);

                tvFullName.setVisibility(View.GONE);
                tvAge.setVisibility(View.GONE);
                tvGender.setVisibility(View.GONE);
                tvContactNumber.setVisibility(View.GONE);
                tvEmail.setVisibility(View.GONE);
                tvAddress.setVisibility(View.GONE);

                etFullName.setVisibility(View.VISIBLE);
                etAge.setVisibility(View.VISIBLE);
                etGender.setVisibility(View.VISIBLE);
                etContactNumber.setVisibility(View.VISIBLE);
                etEmail.setVisibility(View.VISIBLE);
                etAddress.setVisibility(View.VISIBLE);

                btnDone.setVisibility(View.VISIBLE);
            }
        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDone.setVisibility(View.GONE);

                tvEditProfile.setVisibility(View.VISIBLE);

                tvFullName.setVisibility(View.VISIBLE);
                tvAge.setVisibility(View.VISIBLE);
                tvGender.setVisibility(View.VISIBLE);
                tvContactNumber.setVisibility(View.VISIBLE);
                tvEmail.setVisibility(View.VISIBLE);
                tvAddress.setVisibility(View.VISIBLE);

                etFullName.setVisibility(View.GONE);
                etAge.setVisibility(View.GONE);
                etGender.setVisibility(View.GONE);
                etContactNumber.setVisibility(View.GONE);
                etEmail.setVisibility(View.GONE);
                etAddress.setVisibility(View.GONE);
            }
        });



    }
}
