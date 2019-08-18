package com.example.trackyourtrek.System.Collections.Items;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class Challenge {
private PriorityQueue<Journey> journey;
private ArrayList<Walker> patricipates ;
private String challengeID,name,totalDistance;
boolean active;

    public Challenge(String challengeID, String name, String totalDistance, boolean active) {
        this.challengeID = challengeID;
        this.name = name;
        this.totalDistance = totalDistance;
        this.active = active;
        journey= new PriorityQueue<Journey>();//Uses Journey compareTo
        patricipates = new ArrayList<>();
    }
    private Journey findByDistance(int Distance){
        for (Journey journey:journey) {
            if(journey.getDistance()==Distance)
                return journey;
        }
        return null;
    }
    protected void addJourney(Journey journey){
        Journey journeyNew = findByDistance(journey.getDistance());
        if(journeyNew==null){
            this.journey.add(journey);
        }else{
            journeyNew.setMilestone(journey.getMilestone());
        }
    }
    protected void removeJourney(Journey journey){
        this.journey.remove(journey);
    }
    protected void cleanJourneys(){
        journey=new PriorityQueue<>();
    }
    protected void addParticipates(Walker walker){
        if(!patricipates.contains(walker)){
            patricipates.add(walker);
        }
    }
    protected void removeParticipates(Walker walker){
        patricipates.remove(journey);
    }
    protected void cleanParticipates(){
        patricipates= new ArrayList<>();
    }

    public PriorityQueue<Journey> getJourney() {
        return journey;
    }

    public void setJourney(PriorityQueue<Journey> journey) {
        this.journey = journey;
    }

    public ArrayList<Walker> getPatricipates() {
        return patricipates;
    }

    public void setPatricipates(ArrayList<Walker> patricipates) {
        this.patricipates = patricipates;
    }

    public String getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(String challengeID) {
        this.challengeID = challengeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
