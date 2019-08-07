package com.example.north.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.north.R;
import com.example.north.data.Article;

import java.util.ArrayList;

public class ArticleAdaptor extends RecyclerView.Adapter<ArticleAdaptor.ViewHolder> {

    ArrayList<Article> articles = new ArrayList<>();
    Context context;

    public ArticleAdaptor(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View articleRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_row, null);
        return new ViewHolder(articleRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdaptor.ViewHolder viewHolder, int i) {
        Article article = articles.get(i);
        
        viewHolder.title.setText(article.getTitle());
        viewHolder.description.setText(article.getDescription());
        viewHolder.date.setText(article.getPublishedDate());
        viewHolder.authore.setText(article.getAuthore());

        // image set later
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView articleImage;
        public TextView authore, description, title, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.news_row_newsImageId);
            authore = itemView.findViewById(R.id.news_row_authorTitle);
            description = itemView.findViewById(R.id.news_row_descriptionNews);
            title = itemView.findViewById(R.id.news_row_title);
            date = itemView.findViewById(R.id.news_row_dateView);
        }
    }
}