package com.example.north.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.north.controller.AppController;
import com.example.north.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {
    ArrayList<Article> articles = new ArrayList<>();

    public void getNewsList(String url, final ArticleListAsyncResponse callback) {
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
                        article.setNewsUrl(articleObject.getString("url"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishedDate(articleObject.getString("publishedAt"));


                        articles.add(article);
                    }

                    if (null != callback) {
                        // passing to interface method
                       callback.processFinish(articles);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // fire dialog box with error message and maybe redirect back to Home Screen
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
