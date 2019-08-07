package com.example.north.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.north.controller.AppController;
import com.example.north.misc.Config;
import com.example.north.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {
    ArrayList<Article> articles = new ArrayList<>();

    public void getNewsList() {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + Config.NEWS_API_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articleArray = response.getJSONArray("articles");
                    for (int i=0; i<articleArray.length(); i++) {

                        // Create Article Object
                        JSONObject articleObject = articleArray.getJSONObject(i);
                        Article article = new Article();
                        article.setAuthore(articleObject.getString("author"));
                        article.setDescription(articleObject.getString("description"));
                        article.setTitle(articleObject.getString("title"));
                        article.setImageUrl(articleObject.getString("url"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishedDate(articleObject.getString("publishedAt"));


                        articles.add(article);
                    }

                    Log.v("Articles Object: ", articles.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
