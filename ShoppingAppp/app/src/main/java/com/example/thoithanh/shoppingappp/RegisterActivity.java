package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUserId = (EditText) findViewById(R.id.etUserID);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etComfirmPassword = (EditText) findViewById(R.id.etComfirmPassWord);
        final EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) findViewById(R.id.etLastName);
        final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);

        final ImageButton bRegister = (ImageButton) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userId = etUserId.getText().toString();
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String password = etPassword.getText().toString();
                final String comfirmPassword = etComfirmPassword.getText().toString();
                final String phoneNumber = etPhoneNumber.getText().toString();
                final String email = etEmail.getText().toString();

                if (password.equals(comfirmPassword)) {
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    String URL = "http://149.28.26.145:8080/api/login/signup";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", userId);
                        jsonObject.put("password", password);
                        jsonObject.put("fullname", firstName + " " + lastName);
                        jsonObject.put("email", email);
                        jsonObject.put("phone", phoneNumber);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(RegisterActivity.this,"Register successed!",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            RegisterActivity.this.startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this,error.toString() + "\nPlease try again!",Toast.LENGTH_LONG).show();

                        }
                    });


                    queue.add(jsonObjectRequest);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Password does not match",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
