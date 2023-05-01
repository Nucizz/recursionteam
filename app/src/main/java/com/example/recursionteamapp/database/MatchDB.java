package com.example.recursionteamapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recursionteamapp.objects.Article;
import com.example.recursionteamapp.objects.Match;

import java.util.ArrayList;

public class MatchDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public MatchDB(Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
    }

    public ArrayList<Match> getMatch() {
        ArrayList<Match> itemList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM matches", null);

        data.moveToFirst();
        while (!data.isAfterLast()){
            itemList.add(new Match(data.getInt(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getFloat(8)));
            data.move(1);
        }

        db.close();
        return itemList;
    }

    public boolean checkMatch() {
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM matches", null);
        int count = data.getCount();

        db.close();
        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    public Match getMatch(int id) {
        Match item;
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM matches WHERE id = ?", new String[]{String.valueOf(id)});

        data.moveToNext();
        item = new Match(data.getInt(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getFloat(8));

        db.close();
        return item;
    }

    public void addMatch(Match newMatch) {
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", newMatch.getDate());
        values.put("time", newMatch.getTime());
        values.put("location", newMatch.getLocation());
        values.put("teamname1", newMatch.getTeamName1());
        values.put("teamname2", newMatch.getTeamName2());
        values.put("teamlogourl1", newMatch.getTeamLogoURL1());
        values.put("teamlogourl2", newMatch.getTeamLogoURL2());
        values.put("ticketprice", String.valueOf(newMatch.getTicketPrice()));

        db.insert("matches", null, values);
        db.close();
    }
}
