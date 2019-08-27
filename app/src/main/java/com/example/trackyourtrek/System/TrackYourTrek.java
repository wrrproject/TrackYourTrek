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
    private static final String ADMINPASSWORD="12345Six";
    private static final TrackYourTrek ourInstance = new TrackYourTrek();
    private static final ArrayList<Walker> walkers = new ArrayList<>();
    private static final ArrayList<Challenge> challenges = new ArrayList<>();
    private static final ArrayList<Milestone> milestones = new ArrayList<>();
    private static final ArrayList<Journey> journeys = new ArrayList<>();
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
     * <p>Registers walker instance with static list</p>
     * @param walker walker to be added to list.
     */
    public void register(Walker walker){
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
     * @param walker walker which has a progress needs to viewed
     * @return progress f the walker
     */
    public ArrayList<PriorityQueue<ProgressRecord>> viewProgress(Walker walker){
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
     * <p>Joi group procedure</p>
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
     * @param group group to be added
     */
    public void createGroup(Group group){
        groups.add(group);
    }

    ///////////////////////////////////////////////////////////////////////////Package C

    //Use Case; #C0100

    /**
     * <p>Add challenge procedure</p>
     * @param challenge new challene to be added to the list
     */
    public void addChallenge(Challenge challenge){
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
     * @param journey new journey to bed added to list
     */
    public void addJourney(Journey journey){
        journeys.add(journey);
    }

    //Use Case; #C0800
    /**
     * <p>Add milestone procedure</p>
     * @param milestone new milestone to be added to the list
     */
    public void addMilestone(Milestone milestone){
        milestones.add(milestone);
    }

    public void removeWalker(Walker walker){
        walkers.remove(walker);
    }

    public void removeChallenge(Challenge challenge){
        challenges.remove(challenge);
    }
    //Use Case; #C0900
    /**
     * <p>Remove milestone procedure</p>
     * @param milestoneID milestone to be removed from the list
     */
    public void removeMilestone(String  milestoneID){
        Milestone milestone=null;
        for (Milestone temp:milestones) {
            if(temp.getMilestoneID().equals(milestoneID)){
                milestone=temp;
                break;
            }
        }
        milestones.remove(milestone);
    }

    public static ArrayList<Walker> getWalkers() {
        return walkers;
    }

    public static TrackYourTrek getInstance() {
        return ourInstance;
    }

    private TrackYourTrek() {

    }
}
