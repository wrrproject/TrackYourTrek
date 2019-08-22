package com.example.trackyourtrek.System;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Walker;

import java.util.ArrayList;

public class Controler {
    //////////////////////////////////////////All Controller List Data
    ArrayList<Walker> walkers;
    ArrayList<Challenge> challenges;
    ArrayList<Journey> journeys;
    ArrayList<Milestone> milestones;
    /////////////////////////////////////////Initialization of controller
    AppCompatActivity app;
    public Controler(AppCompatActivity app) {
        walkers= new ArrayList<>();
        challenges= new ArrayList<>();
        journeys= new ArrayList<>();
        milestones= new ArrayList<>();
        this.app=app;
    }
    //////////////////////////////////////////All Package-A Use Cases
    //Use Case Id A0100
    protected void Login(){

    }
    //Use Case Id A0200
    protected void Logout(){

    }
    //Use Case Id A0300
    protected void ViewAccountDetails(){

    }
    //Use Case Id A0400
    protected void EditDetails(){

    }
    //////////////////////////////////////////All Package-B Use Cases
    //Use Case Id B0100
    protected void Register(){

    }
    //Use Case Id B0200
    protected void JoinChallenge(){

    }
    //Use Case Id B0300
    protected void RecordSteps(){

    }
    //Use Case Id B0400
    protected void ViewProgress(){

    }
    //Use Case Id B0500
    protected void CompareProgress(){

    }
    //Use Case Id B0600
    protected void Search(){

    }
    //Use Case Id B0700
    protected void JoinGroup(){

    }
    //Use Case Id B0800
    protected void CreateGroup(){

    }
    //////////////////////////////////////////All Package-C Use Cases
    //Use Case Id C0100
    protected void AddChallenge(){

    }
    //Use Case Id C0200
    protected void ViewChallenge(){

    }
    //Use Case Id C0300
    protected void ViewMilestones(){

    }
    //Use Case Id C0400
    protected void EndChallenge(){

    }
    //Use Case Id C0500
    protected void AddJourney(){

    }
    //Use Case Id C0600
    protected void UpdateChallenge(){

    }
    //Use Case Id C0700
    protected void ViewJourneys(){

    }
    //Use Case Id C0800
    protected void AddMilestone(){

    }
    //Use Case Id C0900
    protected void RemoveChallenge(){

    }
    //Use Case Id C1000
    protected void UpdateJourney(){

    }
    //Use Case Id C1100
    protected void RemoveJourney(){

    }

}
