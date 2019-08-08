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
import com.example.north.model.Article;
import com.squareup.picasso.Picasso;

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
        viewHolder.date.setText(Util.dateFormetted(article.getPublishedDate()));
        viewHolder.author.setText(article.getAuthore());

        // image set later
        Picasso.with(context)
                .load(article.getImageUrl())
                .placeholder(R.drawable.photoapp)
                .resize(650, 400)
                .into(viewHolder.articleImage);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView author, description, title, date;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.news_row_date);
            articleImage = itemView.findViewById(R.id.news_row_newsImageId);
            title = itemView.findViewById(R.id.news_row_title);
            author = itemView.findViewById(R.id.news_row_authorTitle);
            description = itemView.findViewById(R.id.news_row_descriptionNews);
        }
    }
}
