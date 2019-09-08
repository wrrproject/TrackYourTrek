package com.example.trackyourtrek.System.Collections.Items;

import com.example.trackyourtrek.System.TrackYourTrek;

import java.io.Serializable;
import java.util.ArrayList;

public class Challenge implements Serializable {
    private int challengeID, totalDistance;
    private String name;
    private boolean active;

    public Challenge(int challengeID, String name, int totalDistance, boolean active) {
        this.challengeID = challengeID;
        this.name = name;
        this.totalDistance = totalDistance;
        this.active = active;
    }

    public int getChallengeID() {
        return challengeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static Challenge newInstance() {
        ArrayList<Challenge> challenges = TrackYourTrek.getChallenges();
        int cID = challenges.get(challenges.size() - 1).getChallengeID() + 1;
        Challenge challenge = new Challenge(cID, "name", 0, true);
        return challenge;
    }

    @Override
    public String toString() {
        return "Challenge " + name + '\'' +
                ", totalDistance='" + totalDistance;
    }

//    /**
//     * Returns the journey at the distance in the journey list
//     *
//     * @param distance
//     * @return
//     */
//    public Journey findJourney(int distance) {
//        for (Journey iterm : journey) {
//            if (iterm.getDistance() == distance) {
//                return iterm;
//            }
//        }
//        return null;
//    }

    public void setChallenge(Challenge challenge) {
        setActive(challenge.active);
        setName(challenge.name);
        setTotalDistance(challenge.totalDistance);
    }

//    public Journey findByDistance(int Distance) {
//        for (Journey journey : journey) {
//            if (journey.getDistance() == Distance)
//                return journey;
//        }
//        return null;
//    }

//    public void addJourney(Journey journey) {
//        Journey journeyNew = findByDistance(journey.getDistance());
//        if (journeyNew == null) {
//            this.journey.add(journey);
//        } else {
//            journeyNew.setMilestone(journey.getMilestone());
//        }
//    }

//    public void removeJourney(Journey journey) {
//        this.journey.remove(journey);
//    }

//    public void cleanJourneys() {
//        journey = new PriorityQueue<>();
//    }

//    public void addParticipates(Walker walker) {
//        participates.add(walker);
//        if (!participates.contains(WalkerActivity)) {
//            participates.add(WalkerActivity);
//        }
//    }

//    public void removeParticipates(Walker walker) {
//        participates.remove(journey);
//    }

//    public void cleanParticipates() {
//        participates = new ArrayList<>();
//    }

//    public PriorityQueue<Journey> getJourney() {
//        return journey;
//    }

//    public void setJourney(PriorityQueue<Journey> journey) {
//        this.journey = journey;
//    }

//    public ArrayList<Walker> getParticipates() {
//        return participates;
//    }

//    public void setParticipates(ArrayList<Walker> participates) {
//        this.participates = participates;
//    }
}
