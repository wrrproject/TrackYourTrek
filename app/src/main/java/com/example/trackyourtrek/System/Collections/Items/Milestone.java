package com.example.trackyourtrek.System.Collections.Items;

import java.util.ArrayList;

public class Milestone {
    private String milestoneID, location,fact;
    private ArrayList<Challenge> usingChallenges = new ArrayList<>();
    public Milestone(String milestoneID, String location, String fact) {
        this.milestoneID = milestoneID;
        this.location = location;
        this.fact = fact;
    }
    protected void addChallenge(Challenge challenge){
        usingChallenges.add(challenge);
    }
    protected void removeChallenge(Challenge challenge){
        usingChallenges.remove(challenge);
    }
    protected void cleanChallenges(){
        usingChallenges= new ArrayList<>();

    }

    public String getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(String milestoneID) {
        this.milestoneID = milestoneID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public ArrayList<Challenge> getUsingChallenges() {
        return usingChallenges;
    }

    public void setUsingChallenges(ArrayList<Challenge> usingChallenges) {
        this.usingChallenges = usingChallenges;
    }
}
