package com.example.trackyourtrek.System;

import com.example.trackyourtrek.System.Collections.Group;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.ProgressRecord;
import com.example.trackyourtrek.System.Collections.Items.TrekAdmin;
import com.example.trackyourtrek.System.Collections.Items.TrekUser;
import com.example.trackyourtrek.System.Collections.Items.Walker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class TrackYourTrek {
    //All System Variables
    private static final String ADMINUSERNAME="GayOverLord69";
    private static final String ADMINPASSWORD="123456";
    private static final TrackYourTrek ourInstance = new TrackYourTrek();
    private static final ArrayList<Walker> walkers = new ArrayList<>();
    private static final ArrayList<Challenge> challenges = new ArrayList<>();
    private static final ArrayList<Milestone> milestones = new ArrayList<>();

    public static ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public static ArrayList<Milestone> getMilestones() {
        return milestones;
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    private static final ArrayList<Group> groups = new ArrayList<>();
    private static final TrekAdmin admin = new TrekAdmin(ADMINUSERNAME,ADMINPASSWORD,"Dr. long","gong","drlongGong@gmail.com","0860030303");

    //All User Cases with javadocs...


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Package A
    //Use Case; #A0100

    /**
     * <p>login function</p>
     * <p>Checks if account aadmin user or walker in list</p>
     * @param username Username to be checked Cap sensitive
     * @param password Password to be checked Cap sensitive
     * @return registered user or Null if incorrect details given
     */
    public TrekUser logIn(String username, String password){
      //Checks if admin
        if(username.concat(password).equals(ADMINUSERNAME.concat(ADMINPASSWORD))){
            return admin;
        }else{
            for (Walker walker:walkers) {
                if((walker.getUsername().equals(username))&&(walker.getPassword().equals(password))){
                    return walker;
                }
            }
        }
        return null;
    }

    //Use Case; #A0200
    /**
     * <p>Log out procedure</p>
     * <p>Just invokes the save to database function</p>
     */
    public void logout(){
        saveToDataBase();
    }

    private void saveToDataBase(){
        //Kades Baby

    }
    public void loadFromDataBase(){
        //Kades Baby
        //Gonna be called everytime the app starts first then we get the lists...

    }

    //Use Case; #A0300
    /**
     * <p>View Account Details Function</p>
     * <p>Takes a username and returns the instance variable owning the username</p>
     * @param username username of user you wish to fetch from system
     * @return
     */
    public TrekUser viewAccountDetails(String username){
        if(username.equals(ADMINUSERNAME)){
            return admin;
        }else{
            for (Walker walker:walkers) {
                if((walker.getUsername().equals(username))){
                    return walker;
                }
            }
        }
        return null;
    }

    //Use Case; #A0400
    /**
     * <p>Edit Account Details Procedure</p>
     * <p>Takes a username and the new instance variable owning the username</p>
     * @param username username of new user you wish to put on the system
     */
    public boolean editAccountDetails(String username, TrekUser trekUser){
        if(username.equals(ADMINUSERNAME)){
            admin.setDetails((TrekAdmin) trekUser);
            return true;
        }else {
            for (Walker walker : walkers) {
                if ((walker.getUsername().equals(username))) {
                    walker.setDetails((Walker) trekUser);
                    return true;
                }
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////Package B

    //Use Case; #B0100
    /**
     * <p>Register Walker Procedure</p>
     * <p>Wont add if username already in the list...</p>
     * <p>Registers walker instance with static list</p>
     * @param walker walker to be added to list.
     */
    public void register(Walker walker){
        Walker temp = findWalker(walker.getUsername());
        if(temp==null)
        walkers.add(walker);
    }

    //Use Case; #B0200
    /**
     * <p>Join Challenge Procedure</p>
     * <p>Adds the walker as a participant of a challenge and the challenge it adds it to the walkers active challenges.</p>
     * @param challenge challenge to which the walker wants to join
     * @param walker walker wanting to join challenge
     */
    public void JoinChallenge(Challenge challenge,Walker walker){
        challenge.addParticipates(walker);
        walker.addhallenge(challenge);
    }

    //Use Case; #B0300
    /**
     * <p>Record Steps</p>
     * <p>Takes username of user in and records step to current system date.</p>
     * @param username username of walker
     * @param steps steps walked by user in steps...
     */
    public void recordSteps(String username, int steps){
        if(username.equals(ADMINUSERNAME)){
            return;//admin cant do it
        }else {
            for (Walker walker : walkers) {
                if ((walker.getUsername().equals(username))) {
                    Calendar c = Calendar.getInstance();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    walker.recordSteps(steps,dateFormat.format(date));//2016/11/16 12:08:43
                    return;
                }
            }
        }
    }

    //Use Case; #B0400
    /**
     * <p>view progress function</p>
     * <p>Takes a walker in and returns the arraylist of proirty queues of progress reports</p>
     * <p>This sounds ikky but each proity queue relates a a active challenge the user is currently participating in</p>
     * @param walkerUsername walker which has a progress needs to viewed
     * @return progress f the walker
     */
    public ArrayList<PriorityQueue<ProgressRecord>> viewProgress(String walkerUsername){
        Walker walker = findWalker(walkerUsername);
        if(walker==null)
            return null;
        return walker.getHistory();
    }

    //Use Case; #B0500
    /**
     * <p>Compare To Function</p>
     * <p>Takes a walker in and returns another random walker that does not have the same username as walker taken in by method</p>
     * @param walker walker not being compared too
     * @return walker that is compareable
     */
    public Walker compareProgress(Walker walker){
        if(walkers.size()<=1){
            return walker;
        }
        Random random = new Random();
        int pos = random.nextInt();
        pos=pos%walkers.size();
        Walker comparer = walkers.get(pos);
        while(comparer.equals(walker)){
            pos=pos++;
            pos=pos%walkers.size();
            comparer = walkers.get(pos);
        }
        return comparer;
    }

    //Use Case; #B0600
    /**
     * <p>Search Group Function</p>
     * <p>Takes a name of a group and returns a list of all groups in system that contains the name given.</p>
     * @param name name of the group
     * @return list of groups conating the name given as its name
     */
    public ArrayList<Group> searchGroup(String name){
        ArrayList<Group> result = new ArrayList<>();
        for (Group grp:groups) {
            if(grp.getGroupName().contains(name)){
                result.add(grp);
            }
        }
        return result;
    }

    //Use Case; #B0700
    /**
     * <p>Join group procedure</p>
     * <p>Takes the id of the group searches for it and like wise for user name and the adds group to walker and walker to list of walkers for group</p>
     * @param groupID ID of thee group adding too
     * @param walkerUsername Username of walker being added
     */
    public void joinGroup(String groupID, String walkerUsername){
        Walker walkerTemp=null ;
        for (Walker walker : walkers) {
            if ((walker.getUsername().equals(walkerUsername))) {
                walkerTemp=walker;
                break;
            }
        }
        if(walkerTemp==null){
            return;
        }
        Group group=null;
        for (Group group1 : groups) {
            if ((group1.getGroupID().equals(groupID))) {
                group=group1;
                break;
            }
        }
        if(group!=null){
            group.add(walkerTemp);
            walkerTemp.setGroup(group);
        }
    }
    //Use Case; #B0800

    /**
     * <p>Create group procedure</p>
     * <p>simple adds new group to groups list</p>
     * <p>Wont add if the group has the same ID as another groups</p>
     * @param group group to be added
     */
    public void createGroup(Group group){
        Group temp = findGroup(group.getGroupID());
        if(temp==null)
        groups.add(group);
    }

    ///////////////////////////////////////////////////////////////////////////Package C

    //Use Case; #C0100

    /**
     * <p>Add challenge procedure</p>
     * @param challenge new challene to be added to the list
     */
    public void addChallenge(Challenge challenge){
        if(findChallenge(challenge.getChallengeID())==null)
        challenges.add(challenge);
    }

    //Use Case; #C0200
    /**
     * <p>View Challenges Function</p>
     * <p>Returns all the challenges in list on system</p>
     * @return the list of challenges on the system
     */
    public static ArrayList<Challenge> viewChallenges() {
        return challenges;
    }

    //Use Case; #C0300
    /**
     * <p>View Milestones Function</p>
     * @return returns all milestones in list
     */
    public static ArrayList<Milestone> viewMilestones() {
        return milestones;
    }

    //Use Case; #C0400
    /**
     * <p>end challenge procedure</p>
     * <p>Takes the id of the challenge and finds it on system and deactivates it.</p>
     * @param challengeID ID of the challenge to be deactivated
     */
    public void endChallenge(String challengeID){
        Challenge challenge=null;
        for (Challenge temp:challenges) {
            if(temp.getChallengeID().equals(challengeID)){
                challenge=temp;
                break;
            }
        }
        if(challenge==null){
            return;
        }
        challenge.setActive(false);
    }

    //Use Case; #C0500

    /**
     * <p>Adds Journey procedure</p>
     * <p>If journey already at the distance in challenges journey then it wont add, you will have to use edit journey for that</p>
     * @param ChallengeID ID of challenge you wish to add the journey too.
     * @param journey new journey to bed added to list
     */
    public void addJourney(String ChallengeID, Journey journey){
        Challenge challenge = findChallenge(ChallengeID);
        if(challenge!=null){
            Journey temp = challenge.findByDistance(journey.getDistance());
            if(temp==null){
             challenge.addJourney(journey);
            }
        }

    }

    //Use Case; #C0800

    /**
     * <p>Edit Challenge Procedure</p>
     * <p>Puts the given challenge at the give id in list.</p>
     * @param ID
     * @param challenge
     */
    public void updateChallenge(String ID, Challenge challenge){
        Challenge challengeSearch = findChallenge(ID);
        if(challengeSearch!=null){
            challengeSearch.setCHallenge(challenge);
        }
    }

    //Use Case; #C0700

    /**
     * <p>View Journey Function</p>
     * <p>Takes a ID ofa challenge and rturns its journey on the system</p>
     * @param challengeID
     * @return journey of the challenge
     */
    public PriorityQueue<Journey> viewJourney(String challengeID){
        Challenge challenge = findChallenge(challengeID);
        if(challenge!=null){
            return challenge.getJourney();
        }
        return null;
    }

    //Use Case; #C0800
    /**
     * <p>Add milestone procedure</p>
     * @param milestone new milestone to be added to the list
     */
    public void addMilestone(Milestone milestone){
        if(findMilestone(milestone.getMilestoneID())==null)
        milestones.add(milestone);
    }

    //Use Case; #C0900
    /**
     * <p>Remove milestone procedure</p>
     * <p>Checks if its being used first in a journey, and if so doesnt remove it</p>
     * @param milestoneID milestone to be removed from the list
     */
    public void removeMilestone(String  milestoneID){
        Milestone milestone=findMilestone(milestoneID);
        if(milestone==null){
            return;
        }
        if(milestone.getUsingChallenges().size()==0)
        milestones.remove(milestone);
    }

    /**
     * <p>Edit Milestone Procedure</p>
     * <p>Takes old usernamer name finds the instance of the old record and putts new one in that is given.</p>
     * @param ID
     * @param milestone
     */
    public void editMilestone(String ID, Milestone milestone){
        Milestone milestoneOld = findMilestone(ID);
        if(milestone!=null){
            milestoneOld.setMilestone(milestone);
        }else{
            milestones.add(milestone);
        }

    }



    //Use Case; #C1000
    /**
     * <p>update Journey procedure</p>
     * <p>Takes the id of the challenge finds t on the system and puts the list of journeys there.</p>
     * @param challengeID
     * @param journey This is a priority queue of journeys!
     */
    public void updateJourney(String challengeID, PriorityQueue<Journey> journey){
        Challenge challenge = findChallenge(challengeID);
        if(challenge!=null){
            challenge.setJourney(journey);
        }
        //asigns the challenges tomilestones for integrity of data
        for (Journey temp:journey) {
            temp.getMilestone().addChallenge(temp.getChallenge());
        }
    }
    //Use Case; #C1100

    /**
     * <p>Remove journey procedure</p>
     * <p>Removes all journeys in the list of the challenge on the system with the challenge ID give.</p>
     * @param ChallengeID
     */
    public void removeJourney(String ChallengeID){
        Challenge challenge = findChallenge(ChallengeID);
        if(challenge!=null){
            challenge.cleanJourneys();
        }
    }
    /**
     * <p>Remove journey procedure</p>
     * <p>Removes all provide journey in the list of the challenge on the system with the challenge ID give.</p>
     * @param ChallengeID
     * @param journey
     */
    public void removeJourney(String ChallengeID,Journey journey){
        Challenge challenge = findChallenge(ChallengeID);
        if(challenge!=null){
            challenge.removeJourney(journey);
        }
    }

    public void removeWalker(Walker walker){
        walkers.remove(walker);
    }

    public void removeChallenge(Challenge challenge){
        challenges.remove(challenge);
    }


    public static ArrayList<Walker> getWalkers() {
        return walkers;
    }

    public static TrackYourTrek getInstance() {
        return ourInstance;
    }
    private TrackYourTrek() {

    }
    public Walker findWalker(String username){
        for (Walker walker:walkers) {
            if(walker.getUsername().equals(username)){
                return walker;
            }
        }
        return null;
    }
    private Challenge findChallenge(String ID){
        for (Challenge challenge:challenges) {
            if(challenge.getChallengeID().equals(ID)){
                return challenge;
            }
        }
        return null;
    }
    private Milestone findMilestone(String ID){
        for (Milestone milestone:milestones) {
            if(milestone.getMilestoneID().equals(ID))
                    return milestone;
        }
        return null;
    }
    private Group findGroup(String ID){
        for (Group group:groups) {
            if(group.getGroupID().equals(ID))
                return group;
        }
        return null;
    }
}
