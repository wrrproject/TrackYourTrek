package com.example.trackyourtrek.System.Collections.Items;

import android.view.View;

import com.example.trackyourtrek.Activites.Admin;
import com.example.trackyourtrek.System.Collections.Group;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class Walker extends TrekUser {
    private Group group;
    private ArrayList<Challenge> activeChallenges = new ArrayList<>();
    private ArrayList<PriorityQueue<ProgressRecord>> history = new ArrayList<PriorityQueue<ProgressRecord>>();
    public void setDetails(Walker walker){
        setGroup(walker.getGroup());
        setEmail(walker.getEmail());
        setfName(walker.getfName());
        setlName(walker.getlName());
        setPassword(walker.getPassword());
    }
    public static Walker newInstance(){
        Walker walker = new Walker("username","password","fName","lName","email");
        return walker;
    }
    public void addhallenge(Challenge challenge){
        activeChallenges.add(challenge);

    }
    public void removeChallenge(Challenge challenge){
        int place = activeChallenges.indexOf(challenge);
        activeChallenges.remove(place);
        history.remove(place);

    }
    public void cleanChallenges(){
        activeChallenges= new ArrayList<>();
    }
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Walker(String username, String password, String fName, String lName, String email, Group group) {
        super(username, password, fName, lName, email);
        this.group = group;
    }

    public Walker(String username, String password, String fName, String lName, String email) {
        super(username, password, fName, lName, email);
    }

    public Group getGroupId() {
        return group;
    }

    public void setGroupId(Group group) {
        this.group=group;
    }

    public void recordSteps(int steps, String Date){
        int place = 0;
        for (Challenge challenge:activeChallenges) {
            ProgressRecord progressRecord = new ProgressRecord(challenge,Date,steps);
            PriorityQueue priorityQueue = history.get(place);
            priorityQueue.add(progressRecord);
            place++;
        }
    }
    @Override
    public String toString() {
        return "Walker ".concat(super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Walker)) return false;
        Walker walker = (Walker) o;
        return walker.getUsername().equals(getUsername());
    }

    public ArrayList<PriorityQueue<ProgressRecord>> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<PriorityQueue<ProgressRecord>> history) {
        this.history = history;
    }
}
