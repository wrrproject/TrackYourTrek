package com.example.trackyourtrek.System.Collections.Items;

public class ProgressRecord implements Comparable<ProgressRecord> {
    Challenge challenge;
    String Date;
    int distance;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ProgressRecord(Challenge challenge, String date, int distance) {
        this.challenge = challenge;
        Date = date;
        this.distance = distance;
    }

    public ProgressRecord(String date, int distance) {
        Date = date;
        this.distance = distance;
    }

    @Override
    public int compareTo(ProgressRecord progressRecord) {
        return Date.compareTo(progressRecord.getDate());
    }
}
