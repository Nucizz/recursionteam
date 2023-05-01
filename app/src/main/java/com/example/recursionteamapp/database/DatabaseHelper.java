package com.example.recursionteamapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "RecursionTeamDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "hashedpassword TEXT NOT NULL)"
        );
        db.execSQL(
                "CREATE TABLE articles (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "imageurl TEXT," +
                        "title TEXT NOT NULL, " +
                        "description TEXT NOT NULL)"
        );
        db.execSQL(
                "CREATE TABLE matches (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "date TEXT NOT NULL, " +
                        "time TEXT NOT NULL, " +
                        "location TEXT NOT NULL," +
                        "teamname1 TEXT NOT NULL, " +
                        "teamlogourl1 TEXT NOT NULL, " +
                        "teamname2 TEXT NOT NULL, " +
                        "teamlogourl2 TEXT NOT NULL, " +
                        "ticketprice FLOAT NOT NULL)");
        db.execSQL(
                "CREATE TABLE transactions (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "matchid INTEGER NOT NULL, " +
                        "userid INTEGER NOT NULL, " +
                        "date TEXT NOT NULL," +
                        "code TEXT NOT NULL," +
                        "type TEXT NOT NULL," +
                        "quantity INTEGER NOT NULL," +
                        "totalprice FLOAT NOT NULL," +
                        "FOREIGN KEY (userid) REFERENCES users(id)," +
                        "FOREIGN KEY (matchid) REFERENCES matches(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS articles");
        db.execSQL("DROP TABLE IF EXISTS matches");
        db.execSQL("DROP TABLE IF EXISTS transactions");
    }
}
