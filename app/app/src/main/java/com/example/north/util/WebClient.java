package com.example.north.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebClient extends WebViewClient {
    private ProgressDialog progressDialog;
    private Context context;

    public WebClient(ProgressDialog progressDialog, Context context) {
        this.progressDialog = progressDialog;
        this.context = context;
        progressDialog.setMessage("Loading");
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressDialog.show();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressDialog.dismiss();
    }
}
