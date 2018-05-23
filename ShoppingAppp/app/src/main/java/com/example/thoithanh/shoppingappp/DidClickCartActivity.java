package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DidClickCartActivity extends AppCompatActivity {

    ArrayList<DccItem> dccItems;
    TextView dcc_tvTotalPrice;
    ImageButton dcc_bPay;

    int userId;
    int totalPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_click_cart);


        //get Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);


        //
        dcc_tvTotalPrice = findViewById(R.id.dcc_tvTotalPrice);
        dcc_bPay = findViewById(R.id.dcc_bPay);

        dcc_bPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DidClickCartActivity.this,DidClickPayActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("price",Integer.valueOf(dcc_tvTotalPrice.getText().toString().substring(1)));
                DidClickCartActivity.this.startActivity(intent);
            }
        });


        initRVItems();
    }
    public void initRVItems(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.dcc_rvItems);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        dccItems = new ArrayList<DccItem>();
//        dccItems.add(new DccItem(0,"","Fourfur tenf loius finouls","$1299.99","1399.00",1));
//        dccItems.add(new DccItem("","Fourfur tenf loius finouls","$1299.99","1399.00",1));
//        dccItems.add(new DccItem("","Fourfur tenf loius finouls","$1299.99","1399.00",1));
//        dccItems.add(new DccItem("","Fourfur tenf loius finouls","$1299.99","1399.00",1));
//        dccItems.add(new DccItem("","Fourfur tenf loius finouls","$1299.99","1399.00",1));
//        dccItems.add(new DccItem("","Fourfur tenf loius finouls","$1299.99","1399.00",1));

        final DccItemAdapter dccItemAdapter = new DccItemAdapter(userId, dccItems,getApplicationContext(),dcc_tvTotalPrice,totalPrice);


        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/cart/" + userId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    totalPrice = 0;
                    for (int i = 0; i<data.length();i++){
                        JSONObject item = data.getJSONObject(i);
                        JSONObject product = item.getJSONObject("product");

                        totalPrice += product.getInt("price")*item.getInt("quantity");

                        final int itemId = product.getInt("id");
                        //String itemURL
                        final String itemName = product.getString("name");
                        String price = "$"+product.getInt("price");
                        final String originalPrice = "$"+product.getInt("price");
                        final int quantity = item.getInt("quantity");

						String url = "http://149.28.26.145:8080/api/products/"+itemId;
						JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								try {
									JSONObject item = response.getJSONObject("data");
									String price = "$"+item.getInt("price");
									String imgURL = item.getJSONArray("picture")
										.getJSONObject(0).getString("link");
									dccItems.add(new DccItem(itemId,imgURL,itemName,price,originalPrice,quantity));
									dccItemAdapter.notifyDataSetChanged();
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Toast.makeText(getApplicationContext(), "Something went wrong!!!", Toast.LENGTH_LONG).show();
							}
						});
						requestQueue.add(jsonObject);

                        //



                    }
                    dcc_tvTotalPrice.setText("$"+totalPrice);
                    dccItemAdapter.notifyDataSetChanged();
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
		recyclerView.setAdapter(dccItemAdapter);
    }
}
