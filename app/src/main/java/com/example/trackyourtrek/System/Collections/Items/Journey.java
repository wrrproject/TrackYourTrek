package com.example.trackyourtrek.System.Collections.Items;

import java.io.Serializable;

public class Journey implements Comparable<Journey>, Serializable {
    private int challengeID, milestoneID, distance;

    public Journey(int challengeID, int milestoneID, int distance) {
        this.challengeID = challengeID;
        this.milestoneID = milestoneID;
        this.distance = distance;
    }

    public int getMilestoneID() {
        return milestoneID;
    }

    public int getChallengeID() {
        return challengeID;
    }

    //    public Challenge getChallenge() {
//        return challenge;
//    }
//
//    public void setChallenge(Challenge challenge) {
//        this.challenge = challenge;
//    }
//
//    public Milestone getMilestone() {
//        return milestone;
//    }
//
//    public void setMilestone(Milestone milestone) {
//        this.milestone = milestone;
//    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Journey journey) {
        return this.getDistance() - journey.getDistance();
    }
}
