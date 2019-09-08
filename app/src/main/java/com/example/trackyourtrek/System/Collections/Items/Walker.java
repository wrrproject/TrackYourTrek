package com.example.trackyourtrek.System.Collections.Items;

public class Walker extends TrekUser {
    private int groupID;

    public void setDetails(Walker walker) {
        setGroupID(walker.getGroupID());
        setEmail(walker.getEmail());
        setfName(walker.getfName());
        setlName(walker.getlName());
        setPassword(walker.getPassword());
    }

    public static Walker newInstance() {
        Walker walker = new Walker("username", "password", "fName", "lName", "email");
        return walker;
    }

    public Walker(String username, String password, String fName, String lName, String email, int group) {
        super(username, password, fName, lName, email);
        this.groupID = group;
    }

    public Walker(String username, String password, String fName, String lName, String email) {
        super(username, password, fName, lName, email);
        groupID = -1;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int group) {
        this.groupID = group;
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
}
