package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUserID = (EditText) findViewById(R.id.etFirstName);
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

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userId = etUserID.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responeListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespond = new JSONObject(response);
                            boolean success = jsonRespond.getBoolean("success");
                            if(success){
                                //json return {success = true,userID,...}
                                String name = jsonRespond.getString("userID");

                                Intent intent = new Intent(MainActivity.this,DidLoginActivity.class);
                                intent.putExtra("name", name);

                                MainActivity.this.startActivity(intent);

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login Failed!")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userId,password,responeListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
