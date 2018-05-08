package com.example.thoithanh.shoppingappp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoithanh on 5/8/18.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "";
    private Map<String, String> params;

    public LoginRequest(String userId, String password, Response.Listener<String> listener){
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("userId",userId);
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
