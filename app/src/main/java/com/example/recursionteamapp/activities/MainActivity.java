package com.example.recursionteamapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.activities.fragment.HomeFragment;
import com.example.recursionteamapp.activities.fragment.ProfileFragment;
import com.example.recursionteamapp.activities.fragment.ScheduleFragment;
import com.example.recursionteamapp.database.DatabaseHelper;
import com.example.recursionteamapp.database.UserDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity {
    BottomNavigationView navbar;

    void _initNavbar() {
        navbar = findViewById(R.id.navigationBar);
        navbar.setOnItemSelectedListener(n -> {
            switch (n.getItemId()) {
                case R.id.home_fragment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new HomeFragment()).commit();
                    return true;
                case R.id.schedule_fragment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new ScheduleFragment()).commit();
                    return true;
                case R.id.profile_fragment:
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new ProfileFragment()).commit();
                    return true;
                default:
                    return false;
            }
        });

        navbar.setSelectedItemId(R.id.home_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _initNavbar();
    }
}