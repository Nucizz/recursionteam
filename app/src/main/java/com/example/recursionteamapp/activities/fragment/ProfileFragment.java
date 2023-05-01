package com.example.recursionteamapp.activities.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.recursionteamapp.Debug;
import com.example.recursionteamapp.R;
import com.example.recursionteamapp.activities.PurchaseHistoryActivity;
import com.example.recursionteamapp.database.TemporaryData;


public class ProfileFragment extends Fragment {

    ImageView displayPicture;
    TextView usernameText, emailText;
    LinearLayout buttonEditProfile,buttonPurchaseHistory, buttonLogout, buttonDebug;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        usernameText = view.findViewById(R.id.userUsername);
        usernameText.setText(TemporaryData.getCurrentUser().getUsername());

        emailText = view.findViewById(R.id.userEmail);
        emailText.setText(TemporaryData.getCurrentUser().getEmail());

        displayPicture = view.findViewById(R.id.userDisplayPicture);

        buttonEditProfile = view.findViewById(R.id.buttonEditProfile);
        buttonEditProfile.setOnClickListener(n -> {
            Toast.makeText(getActivity(), "Ga disuruh... mager :)", Toast.LENGTH_SHORT).show();
        });

        buttonPurchaseHistory = view.findViewById(R.id.buttonPurchaseHistory);
        buttonPurchaseHistory.setOnClickListener(n -> {
            startActivity(new Intent(getContext(), PurchaseHistoryActivity.class));
        });

        buttonDebug = view.findViewById(R.id.buttonDebug);
        buttonDebug.setOnClickListener(n -> {
            Debug._init(getContext());
            getActivity().finish();
            Toast.makeText(getActivity(), "Debug dummy data added!", Toast.LENGTH_SHORT).show();
        });

        buttonLogout = view.findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(n -> {
            TemporaryData.clearData();
            getActivity().finish();
        });

        return view;
    }
}