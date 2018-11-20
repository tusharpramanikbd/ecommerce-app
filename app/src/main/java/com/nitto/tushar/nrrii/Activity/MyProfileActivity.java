package com.nitto.tushar.nrrii.Activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.nitto.tushar.nrrii.R;

public class MyProfileActivity extends AppCompatActivity {

    private AppCompatTextView tvFullName, tvAge, tvGender, tvContactNumber, tvEmail, tvAddress;
    private AppCompatEditText etFullName, etAge, etContactNumber, etEmail, etAddress;
    private AppCompatButton btnDone;
    private FloatingActionButton btnEditProfile;
    private LinearLayout div1, div2, div3, div4, div5, div6;
    private AppCompatSpinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_new);


        initializeUI();


        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditProfile.setVisibility(View.INVISIBLE);

                tvFullName.setVisibility(View.GONE);
                tvAge.setVisibility(View.GONE);
                tvGender.setVisibility(View.GONE);
                tvContactNumber.setVisibility(View.GONE);
                tvEmail.setVisibility(View.GONE);
                tvAddress.setVisibility(View.GONE);

                div1.setVisibility(View.GONE);
                div2.setVisibility(View.GONE);
                div3.setVisibility(View.GONE);
                div4.setVisibility(View.GONE);
                div5.setVisibility(View.GONE);
                div6.setVisibility(View.GONE);

                etFullName.setVisibility(View.VISIBLE);
                etAge.setVisibility(View.VISIBLE);
                etContactNumber.setVisibility(View.VISIBLE);
                etEmail.setVisibility(View.VISIBLE);
                etAddress.setVisibility(View.VISIBLE);

                genderSpinner.setVisibility(View.VISIBLE);

                btnDone.setVisibility(View.VISIBLE);
            }
        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDone.setVisibility(View.GONE);

                btnEditProfile.setVisibility(View.VISIBLE);

                tvFullName.setVisibility(View.VISIBLE);
                tvAge.setVisibility(View.VISIBLE);
                tvGender.setVisibility(View.VISIBLE);
                tvContactNumber.setVisibility(View.VISIBLE);
                tvEmail.setVisibility(View.VISIBLE);
                tvAddress.setVisibility(View.VISIBLE);

                etFullName.setVisibility(View.GONE);
                etAge.setVisibility(View.GONE);
                etContactNumber.setVisibility(View.GONE);
                etEmail.setVisibility(View.GONE);
                etAddress.setVisibility(View.GONE);

                genderSpinner.setVisibility(View.GONE);

                div1.setVisibility(View.VISIBLE);
                div2.setVisibility(View.VISIBLE);
                div3.setVisibility(View.VISIBLE);
                div4.setVisibility(View.VISIBLE);
                div5.setVisibility(View.VISIBLE);
                div6.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeUI() {
        tvFullName = findViewById(R.id.tvFullName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        tvContactNumber = findViewById(R.id.tvContactNumber);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);

        etFullName = findViewById(R.id.etFullName);
        etAge = findViewById(R.id.etAge);
        etContactNumber = findViewById(R.id.etContactNumber);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);

        genderSpinner = findViewById(R.id.genderSpinner);

        btnDone = findViewById(R.id.btnDone);
        btnEditProfile = findViewById(R.id.fabEditProfile);

        div1 = findViewById(R.id.divider1);
        div2 = findViewById(R.id.divider2);
        div3 = findViewById(R.id.divider3);
        div4 = findViewById(R.id.divider4);
        div5 = findViewById(R.id.divider5);
        div6 = findViewById(R.id.divider6);
    }
}
