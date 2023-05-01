package com.example.recursionteamapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recursionteamapp.objects.Match;
import com.example.recursionteamapp.objects.Transaction;

import java.util.ArrayList;

public class TransactionDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    MatchDB matchDB;

    public TransactionDB(Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
        matchDB = new MatchDB(ctx);
    }

    public ArrayList<Transaction> getTransaction() {
        ArrayList<Transaction> itemList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM transactions WHERE userid = ?", new String[]{String.valueOf(TemporaryData.getCurrentUser().getId())});

        data.moveToFirst();
        while (!data.isAfterLast()){
            itemList.add(new Transaction(data.getInt(0), matchDB.getMatch(data.getInt(1)), data.getInt(2), data.getString(3), data.getString(4), data.getString(5), data.getInt(6), data.getFloat(7)));
            data.move(1);
        }

        db.close();
        return itemList;
    }

    public void addTransaction(Transaction newTransaction) {
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("matchid", String.valueOf(newTransaction.getMatchInfo().getId()));
        values.put("userid", String.valueOf(newTransaction.getUserId()));
        values.put("date", newTransaction.getDate());
        values.put("code", newTransaction.getCode());
        values.put("type", newTransaction.getType());
        values.put("quantity", String.valueOf(newTransaction.getQuantity()));
        values.put("totalprice", String.valueOf(newTransaction.getTotalPrice()));

        db.insert("transactions", null, values);
        db.close();
    }

}
