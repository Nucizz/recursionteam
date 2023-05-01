package com.example.recursionteamapp.activities.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recursionteamapp.Debug;
import com.example.recursionteamapp.R;
import com.example.recursionteamapp.activities.MatchDetailsActivity;
import com.example.recursionteamapp.adapter.ArticleAdapter;
import com.example.recursionteamapp.adapter.MatchAdapter;
import com.example.recursionteamapp.database.ArticleDB;
import com.example.recursionteamapp.database.MatchDB;
import com.example.recursionteamapp.objects.Article;
import com.example.recursionteamapp.objects.Match;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    MatchDB matchDB;
    RecyclerView matchView;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    void _initArticle() {
        ArrayList<Match> matchData = matchDB.getMatch();

        MatchAdapter matchAdapter = new MatchAdapter(getContext(), matchData, n -> {
            Intent details = new Intent(getActivity(), MatchDetailsActivity.class);
            details.putExtra("item", n);
            startActivity(details);
        });

        matchView.setAdapter(matchAdapter);
        matchView.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        matchDB = new MatchDB(getActivity());

        matchView = view.findViewById(R.id.matchList);
        _initArticle();

        return view;
    }
}