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
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
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

		//Recommend Items
		//initRVRecommendItems();

        //Poppular Items
        initRVPopItems();




        // for test
        ImageButton btnTest = findViewById(R.id.bMenu);
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
        RecyclerView recyclerView = findViewById(R.id.rvCats);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //arrayCats get from JSON response later
        arrayCats = new ArrayList<Category>();
        arrayCats.add(new Category(1,"Books","Fiction, Science, Business,...",""));
        arrayCats.add(new Category(2,"Electronics","Computer, Camera,...",""));
        arrayCats.add(new Category(3,"Clothes","Shirt, Pants, Shoes,...",""));
        arrayCats.add(new Category(4,"Comestic","Foundation, Mascara,...",""));
        arrayCats.add(new Category(4,"Handmade","Brass, Pen...",""));
        arrayCats.add(new Category(6,"Household","Air conditioner, Bed,...",""));


        CategoryAdapter categoryAdapter = new CategoryAdapter(userId,arrayCats,getApplicationContext());
        recyclerView.setAdapter(categoryAdapter);

    }
    public void initRVPopItems(){
        RecyclerView recyclerView = findViewById(R.id.rvPopItems);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //
        arrayPopItems = new ArrayList<DlgItem>();
		final DlgItemAdapter dlgItemAdapter = new DlgItemAdapter(userId,arrayPopItems,getApplicationContext());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://149.28.26.145:8080/api/products/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,
			null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            	Log.e("DidLoginActivity, data:", response.toString());
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i=0; i<data.length();i++){
                        JSONObject item = data.getJSONObject(i);
                        int itemId = item.getInt("id");
                        String name = item.getString("name");
                        String price = "$"+item.getInt("price");

                        String imgURL = item.getJSONArray("picture")
							.getJSONObject(0).getString("link");
                        arrayPopItems.add(new DlgItem(itemId,name,price,price,imgURL));
						dlgItemAdapter.notifyDataSetChanged();
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
		recyclerView.setAdapter(dlgItemAdapter);

        //array PopItems get from JSON response later
//        arrayPopItems.add(new DlgItem(1,"Watch 1","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch number 2","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch Ellinia af","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch 3","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch 5","$99.99","$119.99",""));
//        arrayPopItems.add(new DlgItem(1,"Watch 6","$99.99","$119.99",""));

    }
    public void initRVRecommendItems(){
      	RecyclerView rvRecommendItems = findViewById(R.id.rvRecommendItems);
		rvRecommendItems.setHasFixedSize(true);
		LinearLayoutManager layoutManagerRec = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
		rvRecommendItems.setLayoutManager(layoutManagerRec);
		//
		arrayPopItems = new ArrayList<DlgItem>();
		final DlgItemAdapter dlgItemAdapterRec = new DlgItemAdapter(userId,arrayPopItems,getApplicationContext());
		RequestQueue requestQueueRec = Volley.newRequestQueue(this);
		String URL = "http://149.28.26.145:8080/api/products/category/2";
		JsonObjectRequest jsonObjectRequestRec = new JsonObjectRequest(Request.Method.GET, URL,
			null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Log.e("DidLoginActivity, data:", response.toString());
				try {
					JSONArray data = response.getJSONArray("data");
					for (int i=0; i<data.length();i++){
						JSONObject item = data.getJSONObject(i);
						int itemId = item.getInt("id");
						String name = item.getString("name");
						String price = "$"+item.getInt("price");

						String imgURL = item.getJSONArray("picture")
							.getJSONObject(0).getString("link");
						arrayPopItems.add(new DlgItem(itemId,name,price,price,imgURL));
						dlgItemAdapterRec.notifyDataSetChanged();
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
		requestQueueRec.add(jsonObjectRequestRec);
		rvRecommendItems.setAdapter(dlgItemAdapterRec);

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
