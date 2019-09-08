package com.example.trackyourtrek.System.Collections.Items;

import com.example.trackyourtrek.System.TrackYourTrek;

import java.io.Serializable;
import java.util.ArrayList;

public class Milestone implements Serializable {
    private int milestoneID;
    private String location, fact;

    public Milestone(int milestoneID, String location, String fact) {
        this.milestoneID = milestoneID;
        this.location = location;
        this.fact = fact;
    }

    public void setMilestone(Milestone milestone) {
        fact = milestone.getFact();
//        milestoneID = milestone.getMilestoneID();
        location = milestone.getLocation();
    }

    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
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

    public static Milestone newInstance() {
        ArrayList<Milestone> milestones = TrackYourTrek.getMilestones();
        int mID = milestones.get(milestones.size() - 1).getMilestoneID() + 1;
        return new Milestone(mID, "Location", "Fact");
    }

    @Override
    public String toString() {
        return location + ", with fact: " + fact;
    }
}
