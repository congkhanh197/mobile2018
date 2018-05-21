package com.example.thoithanh.shoppingappp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class DidClickSearchActivity extends AppCompatActivity {
    EditText etSearch;
    int userId;
    ImageButton dcs_bCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_click_search);

        //get Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);

        //
        etSearch = findViewById(R.id.dcs_etSeach);
        dcs_bCart = findViewById(R.id.dcs_bCart);
        //
        dcs_bCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DidClickSearchActivity.this,DidClickCartActivity.class);
                intent.putExtra("userId", userId);
                DidClickSearchActivity.this.startActivity(intent);
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String title = etSearch.getText().toString();
                    Intent intent = new Intent(DidClickSearchActivity.this,DidSearch.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("title", title);
                    DidClickSearchActivity.this.startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}
