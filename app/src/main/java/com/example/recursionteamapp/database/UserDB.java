package com.example.recursionteamapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recursionteamapp.objects.User;

public class UserDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserDB(Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
    }

    public int validateUser(String username, String hashedPassword) {
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery(
                "SELECT * FROM users WHERE username = ? AND hashedpassword = ?",
                new String[]{username, hashedPassword}
        );
        int count = data.getCount();

        if(count != 0) {
            data.moveToNext();
            int userId = data.getInt(0);
            db.close();
            return userId;
        } else {
            db.close();
            return 0;
        }
    }

    public User getUser(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery(
                "SELECT * FROM users WHERE id = ?",
                new String[]{String.valueOf(id)}
        );
        data.moveToNext();
        User current = new User(data.getInt(0), data.getString(1), data.getString(2), data.getString(3));

        db.close();
        return current;
    }

    public boolean checkUser(String username, String email) {
        db = dbHelper.getReadableDatabase();
        Cursor data1 = db.rawQuery(
                "SELECT * FROM users WHERE username = ?",
                new String[]{username}
        );
        Cursor data2 = db.rawQuery(
                "SELECT * FROM users WHERE email = ?",
                new String[]{email}
        );
        int count = data1.getCount() + data2.getCount();

        db.close();
        if(count != 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addUser(User newUser) {
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", newUser.getUsername());
        values.put("email", newUser.getEmail());
        values.put("hashedpassword", newUser.getHashedPassword());

        db.insert("users", null, values);
        db.close();
    }

}
