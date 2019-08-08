package com.example.north;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.north.data.ArticleData;
import com.example.north.data.ArticleListAsyncResponse;
import com.example.north.model.Article;
import com.example.north.util.ArticleAdaptor;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdaptor articleAdaptor;
    private ProgressDialog dialog;
    private boolean online, isFetched;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFetched = false;

        handler = new Handler();
        online = isOnline();
        handler.post(updateData);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading...");
        dialog.show();
    }

    public void populateList() {
        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
            @Override
            public void processFinish(final ArrayList<Article> articles) {
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(20);
                articleAdaptor = new ArticleAdaptor(articles, getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(articleAdaptor);
                articleAdaptor.setOnClickListener(new ArticleAdaptor.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Article article = articles.get(position);

                        Intent intent = new Intent(getApplicationContext(), NewsDetailActivity.class);
                        intent.putExtra("url", article.getNewsUrl());

                        startActivity(intent);
                        // Toast.makeText(getApplicationContext(), article.getNewsUrl(), Toast.LENGTH_LONG).show();
                    }
                });
                // handler.removeCallbacks(updateData);
                if (dialog.isShowing()) dialog.dismiss();
                isFetched = true;
            }
        });
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    private Runnable updateData = new Runnable() {
        @Override
        public void run() {
            if(online && !isFetched)
                populateList();
            else if (online)  {
                if(dialog.isShowing()) dialog.dismiss();
            } else {
                if(!dialog.isShowing()) {
                    dialog.setMessage("Connecting...");
                    dialog.show();
                }
                Toast.makeText(getApplicationContext(), "You are offline, Check Internet Connection" ,Toast.LENGTH_SHORT).show();
            }
            online = isOnline();
            handler.postDelayed(updateData, 7000);
        }
    };

}
