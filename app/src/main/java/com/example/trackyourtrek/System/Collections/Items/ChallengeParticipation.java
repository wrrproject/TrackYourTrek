package com.example.trackyourtrek.System.Collections.Items;

import java.util.Calendar;

public class ChallengeParticipation {
    private int participationID, challengeID;
    private String username;
    private Calendar startDate, endDate;
    private boolean challengeCompleted;

    public ChallengeParticipation(int participationID, String username, int challengeID,
                                  Calendar startDate, Calendar endDate, boolean challengeCompleted) {
        this.participationID = participationID;
        this.challengeID = challengeID;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.challengeCompleted = challengeCompleted;
    }

    public int getParticipationID() {
        return participationID;
    }

    public int getChallengeID() {
        return challengeID;
    }

    public String getUsername() {
        return username;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public boolean isChallengeCompleted() {
        return challengeCompleted;
    }
}
