package com.example.recursionteamapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.objects.Article;
import com.example.recursionteamapp.objects.Match;
import com.squareup.picasso.Picasso;

public class ArticleDetailsActivity extends Activity {

    Article item;

    ImageView articleThumbnail;
    TextView articleTitle, articleDescription;

    ConstraintLayout backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        item = getIntent().getParcelableExtra("item", Article.class);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(n -> finish());

        articleThumbnail = findViewById(R.id.articleThumbnail);
        if(item.getImageURL() != null) {
            Picasso.get().load(item.getImageURL()).into(articleThumbnail);
        }

        articleTitle = findViewById(R.id.articleTitle);
        articleTitle.setText(item.getTitle());

        articleDescription = findViewById(R.id.articleDescrition);
        articleDescription.setText(item.getDescription());
    }
}