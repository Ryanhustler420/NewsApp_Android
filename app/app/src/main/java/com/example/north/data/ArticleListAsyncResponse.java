package com.example.north.data;

import com.example.north.model.Article;

import java.util.ArrayList;

public interface ArticleListAsyncResponse {
    void processFinish(ArrayList<Article> articles);
}
