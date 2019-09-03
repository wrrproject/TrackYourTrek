package com.example.trackyourtrek.System.Collections.Items;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class Challenge implements Serializable {
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

    /**
     * Returns the journey at the distance in the journey list
     * @param distance
     * @return
     */
    public Journey findJourney(int distance){
        for (Journey iterm:journey) {
            if(iterm.getDistance()==distance){
                return iterm;
            }
        }
        return null;
    }
    public void setCHallenge(Challenge cHallenge){
        setActive(cHallenge.active);
        setJourney(cHallenge.journey);
        setName(cHallenge.name);
        setPatricipates(cHallenge.patricipates);
        setTotalDistance(cHallenge.totalDistance);
    }

    public Journey findByDistance(int Distance){
        for (Journey journey:journey) {
            if(journey.getDistance()==Distance)
                return journey;
        }
        return null;
    }
    public void addJourney(Journey journey){
        Journey journeyNew = findByDistance(journey.getDistance());
        if(journeyNew==null){
            this.journey.add(journey);
        }else{
            journeyNew.setMilestone(journey.getMilestone());
        }
    }
    public void removeJourney(Journey journey){
        this.journey.remove(journey);
    }
    public void cleanJourneys(){
        journey=new PriorityQueue<>();
    }
    public void addParticipates(Walker walker){
        if(!patricipates.contains(walker)){
            patricipates.add(walker);
        }
    }
    public void removeParticipates(Walker walker){
        patricipates.remove(journey);
    }
    public void cleanParticipates(){
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

    @Override
    public String toString() {
        return "Challenge "+ name + '\'' +
                ", totalDistance='" + totalDistance ;
    }

    public static Challenge newInstance(){
        Challenge challenge = new Challenge("id","name","total",false);
        return challenge;
    }
}
