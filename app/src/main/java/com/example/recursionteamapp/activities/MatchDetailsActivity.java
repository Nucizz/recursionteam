package com.example.recursionteamapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recursionteamapp.R;
import com.example.recursionteamapp.adapter.PublicFeature;
import com.example.recursionteamapp.database.TemporaryData;
import com.example.recursionteamapp.database.TransactionDB;
import com.example.recursionteamapp.objects.Match;
import com.example.recursionteamapp.objects.Transaction;
import com.squareup.picasso.Picasso;

public class MatchDetailsActivity extends Activity implements AdapterView.OnItemSelectedListener{

    TransactionDB transactionDB;

    Match item;

    ConstraintLayout backButton;
    ImageView teamLogo1, teamLogo2;
    TextView matchDate, matchTime, matchLocation, totalPriceText, teamName1, teamName2;

    int quantity = 1;
    Button purchaseButton, qtyAdd, qtyMin;
    EditText qtyText;

    double totalPrice;

    Spinner dropdownMenu;
    private static final String[] dropdownItem = {"Public", "Supporter"};
    String selectedSeatingZone = "Public";

    void _initDropdown() {
        dropdownMenu = findViewById(R.id.dropdownSeatingZone);
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dropdownItem);

        dropdownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownMenu.setAdapter(dropdownAdapter);
        dropdownMenu.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                selectedSeatingZone = dropdownItem[0];
                break;
            case 1:
                selectedSeatingZone = dropdownItem[1];
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    String splitter(String input) {
        StringBuilder splitString = new StringBuilder();
        String[] inputArr = input.split(" ");

        splitString.append(inputArr[0]).append(" ");
        splitString.append(inputArr[1]).append("\n");
        int firstLen = splitString.length();

        int len = inputArr.length;
        for (int i=2;i<len;i++) {
            splitString.append(inputArr[i]);
            if(i != len-1) {
                splitString.append(" ");
            }
        }

        int totalLen = splitString.length();
        if(totalLen - firstLen > 10) {
            splitString.replace(firstLen + 8, totalLen, "...");
        }

        return splitString.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        transactionDB = new TransactionDB(this);

        item = getIntent().getParcelableExtra("item", Match.class);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(n -> finish());

        matchDate = findViewById(R.id.matchDate);
        matchDate.setText(splitter(item.getDate()));

        matchTime = findViewById(R.id.matchTime);
        matchTime.setText(item.getTime());

        matchLocation = findViewById(R.id.matchLocation);
        matchLocation.setText(splitter(item.getLocation()));

        teamName1 = findViewById(R.id.teamName1);
        teamName1.setText(item.getTeamName1());

        teamName2 = findViewById(R.id.teamName2);
        teamName2.setText(item.getTeamName2());

        teamLogo1 = findViewById(R.id.teamLogo1);
        if(item.getTeamLogoURL1() != null) {
            Picasso.get().load(item.getTeamLogoURL1()).into(teamLogo1);
        }

        teamLogo2 = findViewById(R.id.teamLogo2);
        if(item.getTeamLogoURL2() != null) {
            Picasso.get().load(item.getTeamLogoURL2()).into(teamLogo2);
        }

        _initDropdown();

        qtyMin = findViewById(R.id.qtyMin);
        qtyAdd = findViewById(R.id.qtyAdd);
        qtyText = findViewById(R.id.qtyText);

        qtyMin.setOnClickListener(n -> {
            qtyHandler(-1);
            updatePrice();
        });

        qtyAdd.setOnClickListener(n -> {
            qtyHandler(1);
            updatePrice();
        });

        qtyText.setOnFocusChangeListener((n, hasFocus) -> {
            if (!hasFocus) {
                quantity = Integer.parseInt(qtyText.getText().toString());
                if(quantity < 1) {
                    Toast.makeText(this, "Quantity must be more than 1!", Toast.LENGTH_SHORT).show();
                    quantity = 1;
                    qtyText.setText(String.valueOf(quantity));
                }
            }
            updatePrice();
        });

        totalPriceText = findViewById(R.id.totalPriceText);
        updatePrice();

        purchaseButton = findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(n -> {purchaseTicket(this);});
    }

    void qtyHandler(int update) {
        quantity += update;
        if(quantity < 1) {
            quantity = 1;
        }
        qtyText.setText(String.valueOf(quantity));
    }

    void updatePrice() {
        totalPrice = quantity * item.getTicketPrice();
        totalPriceText.setText(PublicFeature.formatIDR(totalPrice));
    }

    void purchaseTicket(Context ctx) {
        transactionDB.addTransaction(new Transaction(0, item, TemporaryData.getCurrentUser().getId(), PublicFeature.getDate(), PublicFeature.generateToken(item), selectedSeatingZone, quantity, totalPrice));
        Toast.makeText(ctx, "Tickets purchased!", Toast.LENGTH_SHORT).show();
        finish();
    }
}