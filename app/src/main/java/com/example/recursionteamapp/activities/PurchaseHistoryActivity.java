package com.example.recursionteamapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.recursionteamapp.Debug;
import com.example.recursionteamapp.R;
import com.example.recursionteamapp.adapter.MatchAdapter;
import com.example.recursionteamapp.adapter.PurchaseHistoryAdapter;
import com.example.recursionteamapp.database.TransactionDB;
import com.example.recursionteamapp.objects.Match;
import com.example.recursionteamapp.objects.Transaction;

import java.util.ArrayList;

public class PurchaseHistoryActivity extends Activity {

    TransactionDB transactionDB;
    ConstraintLayout backButton;

    RecyclerView historyView;

    void _initHistory() {
        ArrayList<Transaction> transactionData = transactionDB.getTransaction();

        PurchaseHistoryAdapter purchaseHistoryAdapter = new PurchaseHistoryAdapter(this, transactionData);

        historyView.setAdapter(purchaseHistoryAdapter);
        historyView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        transactionDB = new TransactionDB(this);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(n -> finish());

        historyView = findViewById(R.id.historyView);
        _initHistory();
    }
}