package com.example.trackyourtrek.System.Collections.Items;

import com.example.trackyourtrek.Utility.ListSearches;

import java.io.Serializable;

public class Group implements Serializable {
    private int groupID;
    private String groupName;

    public Group(int groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        int num = ListSearches.findUsersByGroup(groupID).size();
        return "Group: '" + groupName + " with " + num + " members";
    }
}
