package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DidLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_login);

        //et and btn here

        //get Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        //
        String message = "Welcome " + name +  " to DidLoginActivity";
    }
}
