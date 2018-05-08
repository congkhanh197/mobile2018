package com.example.thoithanh.shoppingappp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUserID = (EditText) findViewById(R.id.etUserID);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final ImageButton bLogin = (ImageButton) findViewById(R.id.bLogin);
        final ImageButton bCreateNewID = (ImageButton) findViewById(R.id.bCreateNewId);
        final ImageButton bForgotPassword = (ImageButton) findViewById(R.id.bForgotPassword);

        bCreateNewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
           }
        });

    }
}
