package com.example.recursionteamapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Match implements Parcelable {
    private int id;

    private String date;
    private String time;
    private String location;

    private String teamName1;
    private String teamLogoURL1;

    private String teamName2;
    private String teamLogoURL2;

    private double ticketPrice;

    public Match(int id ,String date, String time, String location, String teamName1, String teamLogoURL1, String teamName2, String teamLogoURL2, double ticketPrice) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
        this.teamName1 = teamName1;
        this.teamLogoURL1 = teamLogoURL1;
        this.teamName2 = teamName2;
        this.teamLogoURL2 = teamLogoURL2;
        this.ticketPrice = ticketPrice;
    }

    protected Match(Parcel in) {
        id = in.readInt();
        date = in.readString();
        time = in.readString();
        location = in.readString();
        teamLogoURL1 = in.readString();
        teamName1 = in.readString();
        teamLogoURL2 = in.readString();
        teamName2 = in.readString();
        ticketPrice = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public String getTeamLogoURL1() {
        return teamLogoURL1;
    }

    public String getTeamName2() {
        return teamName2;
    }

    public String getTeamLogoURL2() {
        return teamLogoURL2;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(location);
        dest.writeString(teamLogoURL1);
        dest.writeString(teamName1);
        dest.writeString(teamLogoURL2);
        dest.writeString(teamName2);
        dest.writeDouble(ticketPrice);
    }
}
