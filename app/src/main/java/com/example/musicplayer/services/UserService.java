package com.example.musicplayer.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.musicplayer.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public class UserService {


    /*public static void login(Context context, String email, String password, ServiceDone cb){

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
        } catch (JSONException error) {
            Toast.makeText(context, "Ocorreu uma falha na requisição!" + error.getMessage(), Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://localhost:8800/auth/test", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                User newUser;
                try {
                    newUser = new User(
                            response.getString("id"),
                            response.getString("name"),
                            response.getString("email")
                    );
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                if (cb != null) {
                    cb.onServiceDone();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Ocorreu uma falha na requisição!" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }*/

}
