package com.example.recursionteamapp.adapter;

import com.example.recursionteamapp.database.TemporaryData;
import com.example.recursionteamapp.objects.Match;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PublicFeature {
    public static String formatIDR(double value) {
        NumberFormat IDR = NumberFormat.getNumberInstance(new Locale("in", "ID"));
        return "IDR " + IDR.format(value);
    }

    public static String shortenDate(String input) {
        StringBuilder newDate = new StringBuilder();
        String[] inputArr = input.split(" ");
        newDate.append(inputArr[0].substring(0, Math.min(inputArr[0].length(), 3))).append(", ");
        newDate.append(inputArr[2].substring(0, Math.min(inputArr[2].length(), 3))).append(" ");
        newDate.append(inputArr[1]);

        return  newDate.toString();
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("E, d MMMM YYYY");
        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());
    }

    public static String generateToken(Match info) {
        StringBuilder token = new StringBuilder();
        token.append(info.getId());

        String[] locArr = info.getLocation().split(" ");
        for (String locSplit : locArr) {
            token.append(locSplit.toUpperCase().charAt(0));
        }

        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Calendar cal = Calendar.getInstance();
        token.append(dateFormat.format(cal.getTime()));

        token.append(TemporaryData.getCurrentUser().getId());

        String[] nameArr = TemporaryData.getCurrentUser().getEmail().split("@");
        int nameLen = nameArr[0].length();
        for (int i=0;i<nameLen;i+=3) {
            token.append(nameArr[0].charAt(i));
        }

        return token.toString();
    }
}
