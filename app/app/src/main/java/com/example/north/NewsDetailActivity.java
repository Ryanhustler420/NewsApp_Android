package com.example.north;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.north.util.WebClient;

public class NewsDetailActivity extends AppCompatActivity {

    WebView webview;
    ProgressDialog progressDialog;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        progressDialog = new ProgressDialog(this);
        webview = findViewById(R.id.news_detail_Webview);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            url = bundle.getString("url");
            webview.setWebViewClient(new WebClient(progressDialog, getApplicationContext()));
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);
        }
    }
}
