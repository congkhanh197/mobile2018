package com.example.thoithanh.shoppingappp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thoithanh on 5/8/18.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "";
    private Map<String, String> params;

    public RegisterRequest(String userId, String firstName, String lastName, String password, String comfirmPassword, String phoneNumber, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("userId",userId);
        params.put("firstName",firstName);
        params.put("lastName",lastName);
        params.put("password",password);
        params.put("comfirmPassword",comfirmPassword);
        params.put("phoneNumber",phoneNumber);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
