package com.example.trackyourtrek.System.Collections;

import com.example.trackyourtrek.System.Collections.Items.Walker;

import java.util.ArrayList;

public class Group {
    private String groupID, groupName;
    final private ArrayList<Walker> groupList = new ArrayList<Walker>();

    public Group(String groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Walker> getGroupList() {
        return groupList;
    }

    public void add(Walker walker){
        groupList.add(walker);
    }
    public void remove(Walker walker){
        groupList.remove(walker);
    }
}
