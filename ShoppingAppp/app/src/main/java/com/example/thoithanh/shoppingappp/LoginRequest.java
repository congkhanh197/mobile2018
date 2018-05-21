package com.example.thoithanh.shoppingappp;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by thoithanh on 5/8/18.
 */

//public class LoginRequest extends StringRequest {
//    private static final String LOGIN_REQUEST_URL = "http://149.28.26.145:8080/api/login";
//    private Map<String, String> params;
//
//    public LoginRequest(String userId, String password, Response.Listener<String> listener){
//        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
//        params = new HashMap<>();
//        params.put("username",userId);
//        params.put("password",password);
//
//    }
//
//    @Override
//    public Map<String, String> getParams() {
//        return params;
//    }
//}

public class LoginRequest extends JsonObjectRequest{
    private static final String LOGIN_REQUEST_URL = "http://149.28.26.145:8080/api/login";


    public LoginRequest(JSONObject jsonObject, Response.Listener<JSONObject> listener) throws JSONException {
        super(Method.POST, LOGIN_REQUEST_URL,jsonObject, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fasdfasf","fadsffjdsalkfhalsdkfjha");
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //This indicates that the reuest has either time out or there is no connection

                } else if (error instanceof AuthFailureError) {
                    // Error indicating that there was an Authentication Failure while performing the request
                    Log.d("fasdfasf","AuthFalure Error");

                } else if (error instanceof ServerError) {
                    //Indicates that the server responded with a error response

                } else if (error instanceof NetworkError) {
                    //Indicates that there was network error while performing the request

                } else if (error instanceof ParseError) {
                    // Indicates that the server response could not be parsed

                }
            }

        });

    }

}
