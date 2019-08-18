package com.example.trackyourtrek.System.Collections.Items;

public class Journey implements Comparable<Journey> {
    private Challenge challenge;
    private Milestone milestone;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    private int distance;



    @Override
    public int compareTo(Journey journey) {
        return this.getDistance()-journey.getDistance();
    }
}
