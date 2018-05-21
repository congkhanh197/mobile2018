package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.lang.reflect.Method;
import java.util.ArrayList;

public class DidClickItem extends AppCompatActivity {
    ArrayList<DcImage> dcImages;
    int userId;
    int itemId;

    TextView dc_tvName;
    TextView dc_tvPrice;
    TextView dc_tvDescription;
    TextView dc_tvInformation;

    ImageView dc_iMainImg;
    EditText dc_etSearch;
    ImageButton dc_bSearch;
    ImageButton dc_bCart;
    ImageButton dc_bMenu;
    TextView dc_tvNumberItemInCart;
    ImageButton dc_bAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_click_item);

        //get Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);
        itemId = intent.getIntExtra("itemId",0);

        //
        dc_tvDescription = findViewById(R.id.dc_tvDescription);
        dc_tvInformation = findViewById(R.id.dc_tvInformation);
        dc_tvName = findViewById(R.id.dc_tvName);
        dc_tvPrice = findViewById(R.id.dc_tvPrice);
        dc_tvNumberItemInCart = findViewById(R.id.dc_tvNumberItemInCart);
        dc_etSearch = findViewById(R.id.dc_etSearch);
        dc_bAdd = findViewById(R.id.dc_bAdd);
        dc_bCart = findViewById(R.id.dc_bCart);
        //


        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("productId",itemId);
            jsonObject.put("quantity",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //
        dc_bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAdd2Cart(jsonObject);
            }
        });
        //
        dc_bCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DidClickItem.this,DidClickCartActivity.class);
                intent.putExtra("userId", userId);
                DidClickItem.this.startActivity(intent);
            }
        });
        //
        dc_etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String title = dc_etSearch.getText().toString();
                    Intent intent = new Intent(DidClickItem.this,DidSearch.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("title", title);
                    DidClickItem.this.startActivity(intent);
                    return true;
                }
                return false;
            }
        });


        //
        setTvNumberItemInCart(dc_tvNumberItemInCart,userId);
        initRequest();
    }
    public void postAdd2Cart(JSONObject jsonObject){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/cart";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                setTvNumberItemInCart(dc_tvNumberItemInCart,userId);
                Toast.makeText(DidClickItem.this, "Added to your cart",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DidClickItem.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        });


        requestQueue.add(jsonObjectRequest);
    }
    public void initRequest(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.dc_rvImages);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/products/" + itemId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    String name = data.getString("name");
                    String info = data.getString("info");
                    String description = data.getString("description");
                    String price = "$" + data.getInt("price");

                    dc_tvName.setText(name);
                    dc_tvPrice.setText(price);
                    dc_tvDescription.setText(description);
                    dc_tvInformation.setText(info);
                    Toast.makeText(DidClickItem.this,name+info+price,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //


        dcImages = new ArrayList<DcImage>();
        dcImages.add(new DcImage(""));
        dcImages.add(new DcImage(""));
        dcImages.add(new DcImage(""));
        dcImages.add(new DcImage(""));
        dcImages.add(new DcImage(""));

        //
        requestQueue.add(jsonObjectRequest);
        //
        DcImageAdapter dcImageAdapter = new DcImageAdapter(dcImages,getApplicationContext());
        recyclerView.setAdapter(dcImageAdapter);
    }
    public void setTvNumberItemInCart(final TextView tvNumberItemInCart, int userId){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String URL = "http://149.28.26.145:8080/api/cart/" + userId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    tvNumberItemInCart.setText(String.valueOf(data.length()));
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
