package com.example.north;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.north.data.ArticleData;
import com.example.north.data.ArticleListAsyncResponse;
import com.example.north.model.Article;
import com.example.north.util.ArticleAdaptor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdaptor articleAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
            @Override
            public void processFinish(ArrayList<Article> articles) {
                recyclerView = findViewById(R.id.recyclerView);
                articleAdaptor = new ArticleAdaptor(articles, getApplicationContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(articleAdaptor);
            }
        });
    }
}
