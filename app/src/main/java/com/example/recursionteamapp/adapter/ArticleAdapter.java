package com.example.recursionteamapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.objects.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Article> data;
    OnClickListener onClickListener;

    public ArticleAdapter(Context ctx, ArrayList<Article> items, OnClickListener onClickListener) {
        this.ctx = ctx;
        this.data = items;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(Article item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_article, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(data.get(position).getImageURL() != null) {
            Picasso.get().load(data.get(position).getImageURL()).into(holder.articleThumbnail);
        }

        holder.articleTitle.setText(data.get(position).getTitle());
        holder.articleDescription.setText(data.get(position).getDescription());

        holder.itemView.setOnClickListener(n -> onClickListener.onClick(data.get(position)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView articleThumbnail;
        TextView articleTitle, articleDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleThumbnail = itemView.findViewById(R.id.thumbnailImage);
            articleTitle = itemView.findViewById(R.id.titleText);
            articleDescription = itemView.findViewById(R.id.descriptionText);
        }
    }

}

