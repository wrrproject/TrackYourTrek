package com.example.trackyourtrek.System;

import com.example.trackyourtrek.Activites.Shared.MainActivity;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.ChallengeParticipation;
import com.example.trackyourtrek.System.Collections.Items.Group;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Progress;
import com.example.trackyourtrek.System.Collections.Items.TrekAdmin;
import com.example.trackyourtrek.System.Collections.Items.TrekUser;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.Utility.Executioner;
import com.example.trackyourtrek.Utility.ListSearches;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class TrackYourTrek {
    private static final TrackYourTrek ourInstance = new TrackYourTrek();

    private static final ArrayList<Group> groups = new ArrayList<>();
    private static final ArrayList<Walker> walkers = new ArrayList<>();
    private static final ArrayList<Challenge> challenges = new ArrayList<>();
    private static final ArrayList<ChallengeParticipation> challengeParticipation = new ArrayList<>();
    private static final ArrayList<Milestone> milestones = new ArrayList<>();
    private static final ArrayList<Journey> journeys = new ArrayList<>();
    private static final ArrayList<Progress> progress = new ArrayList<>();

    public static boolean areThereChanges = false;

    public static ArrayList<Walker> getWalkers() {
        return walkers;
    }

    public static ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public static ArrayList<ChallengeParticipation> getChallengeParticipation() {
        return challengeParticipation;
    }

    public static ArrayList<Milestone> getMilestones() {
        return milestones;
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public static ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public static ArrayList<Progress> getProgress() {
        return progress;
    }

    private static TrekAdmin admin;

    //new TrekAdmin(ADMINUSERNAME,ADMINPASSWORD,"Dr. long","gong","drlongGong@gmail.com","0860030303");
    public static void setAdmin(String username, String password, String fname, String lname,
                                String email, String contactNum) {
        admin = new TrekAdmin(username, password, fname, lname, email, contactNum);
    }

    public static TrekAdmin getAdmin() {
        return admin;
    }

    //All User Cases with javadocs...


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Package A
    //Use Case; #A0100

    /**
     * <p>login function</p>
     * <p>Checks if account admin user or WalkerActivity in list</p>
     *
     * @param username Username to be checked Cap sensitive
     * @param password Password to be checked Cap sensitive
     * @return registered user or Null if incorrect details given
     */
    public static TrekUser logIn(String username, String password) {
        //Checks if admin
        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            return admin;
        } else {
            Walker w = (Walker) ListSearches.findUserByUsername(username);
            if (w != null && w.getPassword().equals(password)) {
                return w;
            }
        }
        return null;
    }

    //Use Case; #A0200

    /**
     * <p>Log out procedure</p>
     * <p>Just invokes the save to database function</p>
     */
    public static void logout() {
//        saveToDataBase();
    }

    public static void saveToDataBase() {
        //Kade's Baby
        if (MainActivity.connected) {
            Executioner exec = new Executioner();
            exec.execute("putBack");
        }
        areThereChanges = false;
    }

    public static void loadFromDataBase() {
        //Kade's Baby
        //Gonna be called every time the app starts first then we get the lists...
        Executioner exec = new Executioner();
        exec.execute("getEverything.php");
    }

    //Use Case; #A0300

    /**
     * <p>View Account Details Function</p>
     * <p>Takes a username and returns the instance variable owning the username</p>
     *
     * @param username username of user you wish to fetch from system
     * @return Returns to user with matching username
     */
    public static TrekUser viewAccountDetails(String username) {
        return ListSearches.findUserByUsername(username);
    }

    //Use Case; #A0400

    /**
     * <p>Edit Account Details Procedure</p>
     * <p>Takes a username and the new instance variable owning the username</p>
     *
     * @param username username of new user you wish to put on the system
     */
    public static boolean editAccountDetails(String username, TrekUser trekUser) {
        //AdminActivity can't edit their details
        for (Walker user : walkers) {
            if (user.getUsername().equals(username)) {
                user.setDetails((Walker) trekUser);
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////Package B

    public static void register(Walker w) {
        walkers.add(w);
        areThereChanges = true;
    }

    //Use Case; #B0200

    /**
     * <p>Join Challenge Procedure</p>
     * <p>Adds the WalkerActivity as a participant of a challenge and the challenge it adds it to the walkers active challenges.</p>
     *
     * @param challenge challenge to which the WalkerActivity wants to join
     * @param walker    WalkerActivity wanting to join challenge
     */
    public static void joinChallenge(Challenge challenge, Walker walker) {
//        challenge.addParticipates(walker);
//        walker.addChallenge(challenge);
        Calendar c = stringToCalendar(getTodayDateAsString());
        ChallengeParticipation participation = new ChallengeParticipation(
                challengeParticipation.size(), walker.getUsername(), challenge.getChallengeID(),
                c, null, false);
        challengeParticipation.add(participation);
    }

    //Use Case; #B0300

    /**
     * <p>Record Steps</p>
     * <p>Takes username of user in and records step to current system date.</p>
     *
     * @param steps steps walked by user in steps...
     */
    public static void recordSteps(int participationID, int steps) {
        int pID = progress.get(progress.size() - 1).getProgressID() + 1;
        Progress p = new Progress(pID, participationID, steps, stringToCalendar(getTodayDateAsString()));
        progress.add(p);
        areThereChanges = true;
    }

    //Use Case; #B0400

    /**
     * <p>view progress function</p>
     * <p>Takes a WalkerActivity in and returns the arraylist of proirty queues of progress reports</p>
     * <p>This sounds ikky but each proity queue relates a a active challenge the user is currently participating in</p>
     *
     * @return progress f the WalkerActivity
     */
    public static ArrayList<Progress> viewProgress(String username, int challengeID) {
        ArrayList<Progress> result = new ArrayList<>();
        ChallengeParticipation participation = ListSearches
                .findParticipationByUsernameAndChallengeID(username, challengeID);
        if (participation != null) {
            return ListSearches.findProgressByParticipationID(participation.getParticipationID());
        } else {
            return new ArrayList<>();
        }
    }

    //Use Case; #B0500

    /**
     * <p>Compare To Function</p>
     * <p>Takes a WalkerActivity in and returns another random WalkerActivity that does not have the same username as WalkerActivity taken in by method</p>
     *
     * @param walker WalkerActivity not being compared too
     * @return WalkerActivity that is compareable
     */
    public static Walker compareProgress(Walker walker) {
        if (walkers.size() <= 1) {
            return walker;
        }
        Random random = new Random();
        int pos = random.nextInt();
        pos = pos % walkers.size();
        Walker comparer = walkers.get(pos);
        while (comparer.equals(walker)) {
            pos = pos++;
            pos = pos % walkers.size();
            comparer = walkers.get(pos);
        }
        return comparer;
    }

    //Use Case; #B0600

    /**
     * <p>Search Group Function</p>
     * <p>Takes a name of a group and returns a list of all groups in system that contains the name given.</p>
     *
     * @param name name of the group
     * @return list of groups conating the name given as its name
     */
    public static ArrayList<Group> searchGroup(String name) {
        ArrayList<Group> result = new ArrayList<>();
        for (Group grp : groups) {
            if (grp.getGroupName().contains(name)) {
                result.add(grp);
            }
        }
        return result;
    }

    //Use Case; #B0700

    /**
     * <p>Join group procedure</p>
     * <p>Takes the id of the group searches for it and like wise for user name and the adds group to WalkerActivity and WalkerActivity to list of walkers for group</p>
     *
     * @param groupID        ID of thee group adding too
     * @param walkerUsername Username of WalkerActivity being added
     */
    public static void joinGroup(int groupID, String walkerUsername) {
        Walker walkerTemp = (Walker) ListSearches.findUserByUsername(walkerUsername);
        if (walkerTemp == null) {
            return;
        }
        Group group = null;
        group = ListSearches.findGroupByID(groupID);
        if (group != null) {
            walkerTemp.setGroupID(group.getGroupID());
            areThereChanges = true;
        }
    }
    //Use Case; #B0800

    /**
     * <p>Create group procedure</p>
     * <p>simple adds new group to groups list</p>
     * <p>Wont add if the group has the same ID as another groups</p>
     */
    public static void createGroup(String groupName) {
        int gID = groups.get(groups.size() - 1).getGroupID() + 1;
        Group g = new Group(gID, groupName);
        groups.add(g);
        areThereChanges = true;
    }

    ///////////////////////////////////////////////////////////////////////////Package C

    //Use Case; #C0100

    /**
     * <p>add challenge procedure</p>
     */
    public static void addChallenge(String name, int distance) {
        int cID = challenges.get(challenges.size() - 1).getChallengeID() + 1;
        Challenge c = new Challenge(cID, name, distance, true);
        challenges.add(c);
        areThereChanges = true;
    }

    //Use Case; #C0200

    /**
     * <p>View Challenges Function</p>
     * <p>Returns all the challenges in list on system</p>
     *
     * @return the list of challenges on the system
     */
    public static ArrayList<Challenge> viewChallenges() {
        return challenges;
    }

    //Use Case; #C0300

    /**
     * <p>View Milestones Function</p>
     *
     * @return returns all milestones in list
     */
    public static ArrayList<Milestone> viewMilestones() {
        return milestones;
    }

    //Use Case; #C0400

    /**
     * <p>end challenge procedure</p>
     * <p>Takes the id of the challenge and finds it on system and deactivates it.</p>
     *
     * @param challengeID ID of the challenge to be deactivated
     */
    public static void endChallenge(int challengeID) {
        Challenge challenge = null;
        for (Challenge temp : challenges) {
            if (temp.getChallengeID() == challengeID) {
                challenge = temp;
                break;
            }
        }
        if (challenge == null) {
            return;
        }
        challenge.setActive(false);
    }

    //Use Case; #C0500

    /**
     * <p>Adds Journey procedure</p>
     * <p>If journey already at the distance in challenges journey then it wont add, you will have to use edit journey for that</p>
     *
     * @param journey new journey to be added to list
     */
    public static void addJourney(Journey journey) {
//        Challenge challenge = findChallenge(ChallengeID);
//        if (challenge != null) {
//            Journey temp = challenge.findByDistance(journey.getDistance());
//            if (temp == null) {
//                challenge.addJourney(journey);
//            }
//        }
        journeys.add(journey);
    }

    //Use Case; #C0800

    /**
     * <p>Edit Challenge Procedure</p>
     * <p>Puts the given challenge at the give id in list.</p>
     */
    public static void updateChallenge(int ID, Challenge challenge) {
        Challenge challengeSearch = ListSearches.findChallengeByID(ID);
        if (challengeSearch != null) {
            challengeSearch.setChallenge(challenge);
        }
    }

    //Use Case; #C0700

    /**
     * <p>View Journey Function</p>
     * <p>Takes a ID of a challenge and returns its journeys sorted in ascending order
     * of distance.</p>
     *
     * @return journeys of the challenge
     */
    public static ArrayList<Journey> viewJourneys(int challengeID) {
        return ListSearches.findJourneysByChallengeID(challengeID);
    }

    //Use Case; #C0800

    /**
     * <p>add milestone procedure</p>
     *
     * @param milestone new milestone to be added to the list
     */
    public static void addMilestone(Milestone milestone) {
        if (ListSearches.findMilestoneByID(milestone.getMilestoneID()) == null)
            milestones.add(milestone);
    }

    //Use Case; #C0900

    /**
     * <p>Remove milestone procedure</p>
     * <p>Checks if its being used first in a journey, and if so doesnt remove it</p>
     *
     * @param milestoneID milestone to be removed from the list
     */
    public static void removeMilestone(int milestoneID) {
        Milestone m = ListSearches.findMilestoneByID(milestoneID);
        if (m != null) {
            milestones.remove(m);
        }
    }

    /**
     * <p>Edit Milestone Procedure</p>
     * <p>Takes old usernamer name finds the instance of the old record and putts new one in that is given.</p>
     */
    public static void editMilestone(int ID, Milestone milestone) {
        Milestone milestoneOld = ListSearches.findMilestoneByID(ID);
        if (milestoneOld != null) {
            milestoneOld.setMilestone(milestone);
        } else {
            milestones.add(milestone);
        }

    }


    //Use Case; #C1000

    /**
     * <p>update Journey procedure</p>
     * <p>Takes the id of the challenge finds t on the system and puts the list of journeys there.</p>
     */
//    public static void updateJourney(int challengeID) {
//        Challenge challenge = findChallenge(challengeID);
//        if (challenge != null) {
//            challenge.setJourney(journey);
//        }
//        //asigns the challenges to milestones for integrity of data
//        for (Journey temp : journey) {
//            temp.getMilestone().addChallenge(temp.getChallenge());
//        }
//    }
    //Use Case; #C1100

    /**
     * <p>Remove journey procedure</p>
     * <p>Removes the journey of milestone with given ID from challenge with given ID</p>
     */
    public static void removeJourney(int challengeID, int milestoneID) {
        Journey j = ListSearches.findJourneyByID(challengeID, milestoneID);
        if (j != null) {
            journeys.remove(j);
        }
    }

    public static void removeWalker(Walker walker) {
        walkers.remove(walker);
    }

    public static void removeChallenge(int challengeID) {
        Challenge c = ListSearches.findChallengeByID(challengeID);
        if (c != null) {
            challenges.remove(c);
        }
    }

    public static TrackYourTrek getInstance() {
        return ourInstance;
    }

    private TrackYourTrek() {
    }

    public static Calendar stringToCalendar(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            df.parse(date);
        } catch (Exception e) {

        }
        return df.getCalendar();
    }

    public static String calendarToString(Calendar cal) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        return df.format(cal.getTime());
    }

    public static String getTodayDateAsString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return df.format(Calendar.getInstance().getTime());
    }
}
