package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DidSearch extends AppCompatActivity {
    ArrayList<DsItem> arrayDsItems;
    TextView ds_tvNumberItemInCart;
    TextView ds_tvTitle;
    int userId;
    int category;
    String title;
    EditText ds_etSearch;
    ImageButton ds_bCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_search);
        // get intent
        //get Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);
        category = intent.getIntExtra("category",-1);
        title = intent.getStringExtra("title");

        //
        ds_tvNumberItemInCart = findViewById(R.id.ds_tvNumberItemInCart);
        ds_tvTitle = findViewById(R.id.ds_tvTitle);
        ds_etSearch = findViewById(R.id.ds_etSeach);
        //
        ds_bCart = findViewById(R.id.ds_bCart);
        ds_bCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DidSearch.this,DidClickCartActivity.class);
                intent.putExtra("userId", userId);
                DidSearch.this.startActivity(intent);
            }
        });
        //
        ds_etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    String title = ds_etSearch.getText().toString();
                    Intent intent = new Intent(DidSearch.this,DidSearch.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("title", title);
                    DidSearch.this.startActivity(intent);


                    return true;

                }
                return false;
            }

        });
        ds_tvTitle.setText(title);

        //
        setTvNumberItemInCart(ds_tvNumberItemInCart,userId);
        initRVDsItems();
    }

    public void initRVDsItems(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvDsItems);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        arrayDsItems = new ArrayList<DsItem>();

        final DsItemAdapter dsItemAdapter = new DsItemAdapter(userId,arrayDsItems,getApplicationContext(),ds_tvNumberItemInCart);
        recyclerView.setAdapter(dsItemAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL;
        if (category == -1){
            URL = "http://149.28.26.145:8080/api/search/"+title;
        }
        else{
            URL = "http://149.28.26.145:8080/api/products/category/"+category;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    for (int i=0; i<data.length();i++){
                        JSONObject item = data.getJSONObject(i);
                        int itemId = item.getInt("id");
                        String name = item.getString("name");
                        Log.d("shoe name", name);
                        String price = "$"+item.getInt("price");
                        String imgURL = "";
                        String description = item.getString("description");
                        arrayDsItems.add(new DsItem(itemId,imgURL,name,description,price,price));
                    }
                    dsItemAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DidSearch.this, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });



//        String des = "Description description description description description description description";
//        arrayDsItems.add(new DsItem("","Watch 1",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 2",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 3",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 4",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 5",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 6",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 7",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 8",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 9",des,"$199.99","$299.99"));
//        arrayDsItems.add(new DsItem("","Watch 10",des,"$199.99","$299.99"));


        requestQueue.add(jsonObjectRequest);

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
