package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DidClickPayActivity extends AppCompatActivity {
    int userId;
    int price;
    ImageButton dcp_bPay;
    ImageButton dcp_bDecline;
    EditText dcp_etPhone;
    EditText dcp_etAddress;
    EditText dcp_etEmail;
    TextView dcp_tvTotalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_click_pay);

        //get Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);
        price = intent.getIntExtra("price",0);


        //
        dcp_bPay = findViewById(R.id.dcp_bPay);
        dcp_bDecline = findViewById(R.id.dcp_bDecline);
        dcp_etPhone = findViewById(R.id.dcp_etPhoneNumber);
        dcp_etAddress = findViewById(R.id.dcp_etAddress);
        dcp_etEmail = findViewById(R.id.dcp_etEmail);
        dcp_tvTotalPrice = findViewById(R.id.dcp_tvTotalPrice);

        //
        initst();

        //
        dcp_bDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DidClickPayActivity.this,DidLoginActivity.class);
                intent.putExtra("userId", userId);
                DidClickPayActivity.this.startActivity(intent);
            }
        });




    }
    public void requestst() throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/order";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("phone",dcp_etPhone.getText());
        jsonObject.put("address",dcp_etAddress.getText());
        jsonObject.put("emailAddress",dcp_etEmail.getText());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(DidClickPayActivity.this,"Sending order successed",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DidClickPayActivity.this,"Sending order failed",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void initst(){
        dcp_tvTotalPrice.setText("$"+price);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/users/" + userId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String email = response.getString("email");
                    String phone = response.getString("phone");
                    dcp_etEmail.setText(email);
                    dcp_etPhone.setText(phone);
                    //String address = response.getString("address");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}
