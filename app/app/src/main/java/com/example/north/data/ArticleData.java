package com.example.north.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.north.controller.AppController;
import com.example.north.misc.Config;
import com.example.north.model.Article;

import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {
    ArrayList<Article> articles = new ArrayList<>();

    public void getNewsList() {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + Config.NEWS_API_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
