package com.example.recursionteamapp.activities.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recursionteamapp.Debug;
import com.example.recursionteamapp.R;
import com.example.recursionteamapp.activities.ArticleDetailsActivity;
import com.example.recursionteamapp.activities.MatchDetailsActivity;
import com.example.recursionteamapp.adapter.ArticleAdapter;
import com.example.recursionteamapp.adapter.PublicFeature;
import com.example.recursionteamapp.database.ArticleDB;
import com.example.recursionteamapp.database.MatchDB;
import com.example.recursionteamapp.database.UserDB;
import com.example.recursionteamapp.objects.Article;
import com.example.recursionteamapp.objects.Match;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArticleDB articleDB;
    MatchDB matchDB;
    RecyclerView articleView;

    ImageView teamLogo1, teamLogo2;
    TextView dateText, timeText, venueText;

    Button bookButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    void _initArticle() {
        ArrayList<Article> articleData = articleDB.getArticle();

        ArticleAdapter articleAdapter = new ArticleAdapter(getContext(), articleData, n -> {
            Intent details = new Intent(getActivity(), ArticleDetailsActivity.class);
            details.putExtra("item", n);
            startActivity(details);
        });

        articleView.setAdapter(articleAdapter);
        articleView.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    void _initLatestMatch() {
        Match latestMatch = matchDB.getMatch(1);
        if(latestMatch.getTeamLogoURL1() != null) {
            Picasso.get().load(latestMatch.getTeamLogoURL1()).into(teamLogo1);
        }

        if(latestMatch.getTeamLogoURL2() != null) {
            Picasso.get().load(latestMatch.getTeamLogoURL2()).into(teamLogo2);
        }

        dateText.setText(PublicFeature.shortenDate(latestMatch.getDate()));

        timeText.setText(latestMatch.getTime());

        String location = latestMatch.getLocation();
        venueText.setText(location.substring(0, Math.min(location.length(), 12)));

        bookButton.setOnClickListener(n -> {
            Intent details = new Intent(getActivity(), MatchDetailsActivity.class);
            details.putExtra("item", latestMatch);
            startActivity(details);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        articleDB = new ArticleDB(getContext());
        matchDB = new MatchDB(getContext());

        articleView = view.findViewById(R.id.articleList);

        teamLogo1 = view.findViewById(R.id.teamLogo1);
        teamLogo2 = view.findViewById(R.id.teamLogo2);
        dateText = view.findViewById(R.id.matchDate);
        timeText = view.findViewById(R.id.matchTime);
        venueText = view.findViewById(R.id.matchLocation);
        bookButton = view.findViewById(R.id.bookButton);

        if(matchDB.checkMatch()) {
            _initLatestMatch();
        }

        _initArticle();

        return view;
    }
}