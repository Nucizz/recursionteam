package com.example.recursionteamapp.database;

import com.example.recursionteamapp.objects.User;

public class TemporaryData {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        TemporaryData.currentUser = currentUser;
    }

    public static void clearData() {
        currentUser = new User(0, "null", "null", "null");
    }
}
