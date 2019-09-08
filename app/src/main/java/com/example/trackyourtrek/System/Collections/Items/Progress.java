package com.example.trackyourtrek.System.Collections.Items;

import java.util.Calendar;

public class Progress {
    private int progressID, participationID, distance;
    private Calendar date;

    public Progress(int progressID, int participationID, int distance, Calendar date) {
        this.progressID = progressID;
        this.participationID = participationID;
        this.distance = distance;
        this.date = date;
    }

    public int getProgressID() {
        return progressID;
    }

    public int getParticipationID() {
        return participationID;
    }

    public int getDistance() {
        return distance;
    }

    public Calendar getDate() {
        return date;
    }
}
