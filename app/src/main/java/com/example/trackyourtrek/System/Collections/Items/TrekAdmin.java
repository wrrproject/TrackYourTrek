package com.example.trackyourtrek.System.Collections.Items;

public class TrekAdmin extends TrekUser {
    private String contactNum;
    public void setDetails(TrekAdmin trekAdmin){
        setPassword(trekAdmin.getPassword());
        setContactNum(trekAdmin.getContactNum());
        setEmail(trekAdmin.getEmail());
        setfName(trekAdmin.getfName());
        setlName(trekAdmin.getlName());

    }
    public TrekAdmin(String username, String password, String fName, String lName, String email, String contactNum) {
        super(username, password, fName, lName, email);
        this.contactNum = contactNum;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    @Override
    public String toString() {
        return "Admin " + super.toString();
    }
}
