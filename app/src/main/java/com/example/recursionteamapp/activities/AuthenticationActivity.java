package com.example.recursionteamapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.recursionteamapp.Debug;
import com.example.recursionteamapp.R;
import com.example.recursionteamapp.activities.fragment.LoginFragment;

public class AuthenticationActivity extends FragmentActivity {

    VideoView background;

    void _initBackground() {
        background = findViewById(R.id.backgroundVideo);
        background.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.opening_bg));
        background.setOnPreparedListener(mp -> {
            mp.setLooping(true);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        _initBackground();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.authenticationContainer, new LoginFragment(), null).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        background.start();
    }
}