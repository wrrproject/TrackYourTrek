package com.example.trackyourtrek.Utility;

import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.ChallengeParticipation;
import com.example.trackyourtrek.System.Collections.Items.Group;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Progress;
import com.example.trackyourtrek.System.Collections.Items.TrekUser;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.util.ArrayList;

public class ListSearches {
    private static final ListSearches ourInstance = new ListSearches();

    public static ListSearches getInstance() {
        return ourInstance;
    }

    private ListSearches() {
    }

    //Change to binary search
    public static Milestone findMilestoneByID(int milestoneID) {
        for (Milestone m : TrackYourTrek.getMilestones()) {
            if (m.getMilestoneID() == milestoneID) {
                return m;
            }
        }
        return null;
    }

    public static Group findGroupByID(int groupID) {
        for (Group g : TrackYourTrek.getGroups()) {
            if (g.getGroupID() == groupID) {
                return g;
            }
        }
        return null;
    }

    public static TrekUser findUserByUsername(String username) {
        if (TrackYourTrek.getAdmin().getUsername().equals(username)) {
            return TrackYourTrek.getAdmin();
        }
        for (TrekUser t : TrackYourTrek.getWalkers()) {
            if (t.getUsername().equals(username)) {
                return t;
            }
        }
        return null;
    }

    public static Challenge findChallengeByID(int challengeID) {
        for (Challenge c : TrackYourTrek.getChallenges()) {
            if (c.getChallengeID() == challengeID) {
                return c;
            }
        }
        return null;
    }

    public static Journey findJourneyByID(int challengeID, int milestoneID) {
        for (Journey j : TrackYourTrek.getJourneys()) {
            if (j.getChallengeID() == challengeID && j.getMilestoneID() == milestoneID) {
                return j;
            }
        }
        return null;
    }

    public static ArrayList<Journey> findJourneysByChallengeID(int challengeID) {
        ArrayList<Journey> result = new ArrayList<>();
        for (Journey j : TrackYourTrek.getJourneys()) {
            if (j.getChallengeID() == challengeID) {
                if (result.size() == 0) {
                    result.add(j);
                } else {
                    boolean added = false;
                    for (int i = 0; i < result.size(); i++) {
                        if (j.compareTo(result.get(i)) <= 0) {
                            result.add(i, j);
                            added = true;
                            break;
                        }
                    }
                    if (!added) {
                        result.add(j);
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<ChallengeParticipation> findParticipationByUsername(String username) {
        ArrayList<ChallengeParticipation> result = new ArrayList<>();
        for (ChallengeParticipation p : TrackYourTrek.getChallengeParticipation()
        ) {
            if (p.getUsername().equals(username)) {
                result.add(p);
            }
        }
        return result;
    }

    public static ChallengeParticipation findParticipationByUsernameAndChallengeID(String username,
                                                                                   int challengeID) {
        for (ChallengeParticipation p : TrackYourTrek.getChallengeParticipation()) {
            if (p.getUsername().equals(username) && p.getChallengeID() == challengeID) {
                return p;
            }
        }
        return null;
    }

    public static ChallengeParticipation findParticipationByID(int participationID) {
        for (ChallengeParticipation p : TrackYourTrek.getChallengeParticipation()) {
            if (p.getParticipationID() == participationID) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Progress> findProgressByParticipationID(int participationID) {
        ArrayList<Progress> result = new ArrayList<>();
        for (Progress p : TrackYourTrek.getProgress()) {
            if (p.getParticipationID() == participationID) {
                result.add(p);
            }
        }
        return result;
    }

    public static ArrayList<Challenge> findChallengesByUsername(String username) {
        ArrayList<Challenge> result = new ArrayList<>();
        ArrayList<ChallengeParticipation> participations = findParticipationByUsername(username);
        for (ChallengeParticipation p : participations) {
            for (Challenge c : TrackYourTrek.getChallenges()) {
                if (p.getChallengeID() == c.getChallengeID()) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    public static Journey findJourneyByChallengeIDAndDistance(int challengeID, int distance) {
        for (Journey j : TrackYourTrek.getJourneys()) {
            if (j.getChallengeID() == challengeID && j.getDistance() == distance) {
                return j;
            }
        }
        return null;
    }

    public static ArrayList<Milestone> findMilestonesNotLinkedWithChallengeID(int challengeID) {
        ArrayList<Milestone> result = new ArrayList<>();
        for (Milestone m : TrackYourTrek.getMilestones()) {
            boolean found = false;
            for (Journey j : TrackYourTrek.getJourneys()) {
                if (j.getMilestoneID() == m.getMilestoneID() && j.getChallengeID() == challengeID) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.add(m);
            }
        }
        return result;
    }
}
