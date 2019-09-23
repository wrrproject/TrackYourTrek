package com.example.trackyourtrek.Activites.Walker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.Activites.Shared.EditWalkerActivity;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.ChallengeParticipation;
import com.example.trackyourtrek.System.Collections.Items.Group;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

import java.util.ArrayList;
import java.util.Stack;

public class WalkerActivity extends AppCompatActivity {
    private final int REQ_GROUP = 5;
    private final int REQ_EDITWALKER = 1;
    Walker currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker);
        if (getIntent() != null) {
            currentUser = (Walker) getIntent().getSerializableExtra("WalkerActivity");
        }
    }

    TextView lblHeading, edtGroupName, edtTotalChallenges;

    @Override
    protected void onStart() {
        super.onStart();

        String username = currentUser.getUsername();
        currentUser = (Walker) ListSearches.findUserByUsername(username);
        if (currentUser == null) {
            finish();
        }
        lblHeading = findViewById(R.id.lblWalkerHeading);
        lblHeading.setText("Welcome, " + currentUser.getUsername() + "!");
        edtGroupName = findViewById(R.id.edtGroupName);
        Group g = ListSearches.findGroupByID(currentUser.getGroupID());
        if (g != null) {
            edtGroupName.setText(g.toString());
        }
        edtTotalChallenges = findViewById(R.id.eedtTotalChallenges);
        edtTotalChallenges.setText("" +
                ListSearches.findParticipationByUsername(currentUser.getUsername()).size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_GROUP && resultCode == RESULT_OK) {
            //Need to extract the new group
            Group group = (Group) data.getExtras().getSerializable("group");
            if (TrackYourTrek.getGroups().indexOf(group) == -1) {
                //TrackYourTrek.createGroup(group.getGroupName());
                TrackYourTrek.joinGroup(group.getGroupID(), currentUser.getUsername());
            }
        } else if (requestCode == REQ_EDITWALKER && resultCode == RESULT_OK) {
            //THen they changed their details
            Walker dummie = (Walker) data.getSerializableExtra("WalkerActivity");
            currentUser.setlName(dummie.getlName());
            currentUser.setfName(dummie.getfName());
            currentUser.setPassword(dummie.getPassword());
            currentUser.setEmail(dummie.getEmail());
            //they cant change their usernames
        }
    }

    public void toGroup(View view) {
        Intent intent = new Intent(this, ActGroup.class);
        intent.putExtra("user", currentUser);
        startActivityForResult(intent, REQ_GROUP);
    }

    public void viewDetails(View view) {
        Intent intent = new Intent(this, EditWalkerActivity.class);
        intent.putExtra("WalkerActivity", currentUser);
        intent.putExtra("username",false);//means you cant change username
        startActivityForResult(intent, REQ_EDITWALKER);
    }

    public void toChallenges(View view) {
        Intent intent = new Intent(this, WalkerChallenges.class);
        intent.putExtra("WalkerActivity", currentUser);
        startActivity(intent);
    }
    TextView edtSteps;
    Stack windowID = new Stack();
    public void toRecord(View view) {
        setContentView(R.layout.record_steps);
        edtSteps=findViewById(R.id.edtSteps);
        windowID.push(R.layout.activity_walker);
    }
    public void RecordSteps(View view){
        //Where to add the record code
    }
    public void BackPressed(View view){
        onBackPressed();
        onStart();
    }

    @Override
    public void onBackPressed() {
        if(windowID.size()==0)
        super.onBackPressed();
        else
            setContentView((int)windowID.pop());

    }

    public void btnLogoutClick(View view) {
        TrackYourTrek.saveToDataBase();
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (TrackYourTrek.areThereChanges) {
            TrackYourTrek.saveToDataBase();
        }
    }
}
