package com.example.thoithanh.shoppingappp;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.List;

public class DidLoginActivity extends AppCompatActivity {
    int userId;

    ListView lvCats;
    ArrayList<Category> arrayCats;
    ArrayList<DlgItem> arrayPopItems;
    ArrayList<DlgItem> arrayRecItems;
    EditText etSearch;
    TextView tvNumberItemInCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_login);

        //get Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);

        //
        etSearch = findViewById(R.id.etSeach);
        tvNumberItemInCart = findViewById(R.id.tvNumberItemInCart);

        //et and btn here
        ImageButton bCart = findViewById(R.id.bCart);
        bCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DidLoginActivity.this,DidClickCartActivity.class);
                intent.putExtra("userId", userId);
                DidLoginActivity.this.startActivity(intent);
            }
        });

        //

        //
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String title = etSearch.getText().toString();
                    Intent intent = new Intent(DidLoginActivity.this,DidSearch.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("title", title);
                    DidLoginActivity.this.startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        //
        String message = "Welcome " + userId +  " to DidLoginActivity";
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();

        //Set cart
        setTvNumberItemInCart(tvNumberItemInCart,userId);

        //Categories
        initRVCat();

        //Poppular Items
        initRVPopItems();

        //Recommend Items
        initRVRecommendItems();


        //for test
        ImageButton btnTest = (ImageButton)findViewById(R.id.bMenu);
        btnTest.setOnClickListener(new View.OnClickListener() {
            //When click specific category, code here:
            @Override
            public void onClick(View v) {


                //String name = "aa";
                Intent intent = new Intent(DidLoginActivity.this,DidSearch.class);
                //intent.putExtra("name", name);
                DidLoginActivity.this.startActivity(intent);
            }
        });

    }
    public void initRVCat(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvCats);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //arrayCats get from JSON response later
        arrayCats = new ArrayList<Category>();
        arrayCats.add(new Category(1,"Books","Fiction, Science, Business,...","1234"));
        arrayCats.add(new Category(2,"Electronics","Computer, Camera,...","1234"));
        arrayCats.add(new Category(3,"Household","Fiction, Science, Business,...","1234"));
        arrayCats.add(new Category(4,"Clothes","Fiction, Science, Business,...","1234"));
        arrayCats.add(new Category(5,"Comestic","Fiction, Science, Business,...","1234"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(userId,arrayCats,getApplicationContext());
        recyclerView.setAdapter(categoryAdapter);

    }
    public void initRVPopItems(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvPopItems);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //
        arrayPopItems = new ArrayList<DlgItem>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/products/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i=0; i<data.length();i++){
                        JSONObject item = data.getJSONObject(i);
                        int itemId = item.getInt("id");
                        String name = item.getString("name");
                        String price = "$"+item.getInt("price");
                        //String imgURL =
                        arrayPopItems.add(new DlgItem(itemId,name,price,price,""));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DidLoginActivity.this, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);




        //array PopItems get from JSON response later
//        arrayPopItems.add(new DlgItem(1,"Watch 1","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch number 2","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch Ellinia af","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch 3","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch 5","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch 6","$99.99","$119.99",""));

        DlgItemAdapter dlgItemAdapter = new DlgItemAdapter(userId,arrayPopItems,getApplicationContext());
        recyclerView.setAdapter(dlgItemAdapter);
    }
    public void initRVRecommendItems(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvRecommendItems);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        ///
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        arrayRecItems = new ArrayList<DlgItem>();
        String URL = "http://149.28.26.145:8080/api/products/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i=0; i<data.length();i++){
                        JSONObject item = data.getJSONObject(i);
                        int itemId = item.getInt("id");
                        String name = item.getString("name");
                        String price = "$"+item.getInt("price");
                        //String imgURL =
                        arrayRecItems.add(new DlgItem(itemId,name,price,price,""));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DidLoginActivity.this, "Something went wrong!!!", Toast.LENGTH_LONG).show();
            }
        });
        ///

//        arrayRecItems = new ArrayList<DlgItem>();
//        arrayRecItems.add(new DlgItem("Watch 1","$99.99","$119.99",""));
//        arrayRecItems.add(new DlgItem("Watch number 2","$99.99","$119.99",""));
        requestQueue.add(jsonObjectRequest);

        DlgItemAdapter dlgItemAdapter = new DlgItemAdapter(userId,arrayRecItems,getApplicationContext());
        recyclerView.setAdapter(dlgItemAdapter);


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
                    Log.d("Taggg", String.valueOf(data.length()));
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
