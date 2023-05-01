package com.example.recursionteamapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recursionteamapp.objects.Article;

import java.util.ArrayList;

public class ArticleDB {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public ArticleDB(Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
    }

    public ArrayList<Article> getArticle() {
        ArrayList<Article> itemList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM articles", null);

        data.moveToFirst();
        while (!data.isAfterLast()){
            itemList.add(new Article(data.getInt(0), data.getString(1), data.getString(2), data.getString(3)));
            data.move(1);
        }

        db.close();
        return itemList;
    }

    public void addArticle(Article newArticle) {
        db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", newArticle.getTitle());
        values.put("description", newArticle.getDescription());
        values.put("imageurl", newArticle.getImageURL());

        db.insert("articles", null, values);
        db.close();
    }

}
