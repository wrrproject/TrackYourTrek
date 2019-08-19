package com.example.trackyourtrek.System;

import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Walker;

import java.util.ArrayList;

public class TrackYourTrek {
    private static final TrackYourTrek ourInstance = new TrackYourTrek();
    private static final ArrayList<Walker> walkers = new ArrayList<>();
    private static final ArrayList<Challenge> challenges = new ArrayList<>();
    private static final ArrayList<Milestone> milestones = new ArrayList<>();
    public void addWalker(Walker walker){
        walkers.add(walker);
    }
    public void addChallenge(Challenge challenge){
        challenges.add(challenge);
    }
    public void addMilestone(Milestone milestone){
        milestones.add(milestone);
    }
    public void removeWalker(Walker walker){
        walkers.remove(walker);
    }
    public void removeChallenge(Challenge challenge){
        challenges.remove(challenge);
    }
    public void removeMilestone(Milestone milestone){
        milestones.remove(milestone);
    }
    public static ArrayList<Walker> getWalkers() {
        return walkers;
    }

    public static ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public static ArrayList<Milestone> getMilestones() {
        return milestones;
    }

    public static TrackYourTrek getInstance() {
        return ourInstance;
    }

    private TrackYourTrek() {

    }
}
