package com.example.trackyourtrek.System.Collections.Items;

import java.io.Serializable;
import java.util.ArrayList;

public class Milestone implements Serializable {
    private String milestoneID, location,fact;
    private ArrayList<Challenge> usingChallenges = new ArrayList<>();
    public Milestone(String milestoneID, String location, String fact) {
        this.milestoneID = milestoneID;
        this.location = location;
        this.fact = fact;
    }
    public void setMilestone(Milestone milestone){
        fact=milestone.getFact();
        milestoneID=milestone.getMilestoneID();
        location=milestone.getLocation();
    }
    public void addChallenge(Challenge challenge){
        if(usingChallenges.indexOf(challenge)<0)
        usingChallenges.add(challenge);
    }
    public void removeChallenge(Challenge challenge){
        usingChallenges.remove(challenge);
    }
    public void cleanChallenges(){
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

    @Override
    public String toString() {
        return location +", with fact: " + fact ;
    }
}
