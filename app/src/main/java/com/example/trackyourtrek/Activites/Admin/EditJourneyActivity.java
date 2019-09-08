package com.example.trackyourtrek.Activites.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Adapters.JourneyAdapter;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

import java.util.ArrayList;

public class EditJourneyActivity extends AppCompatActivity {
    Challenge challenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journey);

        if (getIntent() != null) {
            challenge = (Challenge) getIntent().getExtras().getSerializable("challenge");
        }
    }

    private TextView edtDistance;
    private JourneyAdapter journeyAdapter;
    private Spinner comboBox;

    private ArrayList<Milestone> milestones;
    private ArrayAdapter<Milestone> milestoneAdapter;

    @Override
    protected void onStart() {
        super.onStart();

        TextView lblName = findViewById(R.id.lblChallengeJourney);
        lblName.setText("Name: " + challenge.getName());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(lm);
        journeyAdapter = new JourneyAdapter(ListSearches.findJourneysByChallengeID(challenge.getChallengeID()));
        recyclerView.setAdapter(journeyAdapter);

        milestones = ListSearches.findMilestonesNotLinkedWithChallengeID(challenge.getChallengeID());

        milestoneAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, // a pre-define layout with text views
                android.R.id.text1, // the id of the text view in which to display the toString value
                milestones); // the person collection to be displayed
        comboBox = findViewById(R.id.comboMilestones);
        comboBox.setAdapter(milestoneAdapter);
        edtDistance = findViewById(R.id.edtDistance);

    }

    public void add(View view) {
        int milestonePlace = comboBox.getSelectedItemPosition();
        int distance = Integer.parseInt(edtDistance.getText().toString());
        if (milestonePlace >= 0) {
            Milestone selected = milestoneAdapter.getItem(milestonePlace);
            Journey newPoint = new Journey(challenge.getChallengeID(), selected.getMilestoneID(),
                    distance);
            //newPoint.setChallenge(challenge);
            //newPoint.setMilestone(selected);
            //int distance = Integer.parseInt(edtDistance.getText().toString());
            //newPoint.setDistance(distance);
            //if (challenge.findByDistance(distance) == null && journeyAdapter.findByDisatcne(distance) == null) {
            if (journeyAdapter.findByDistance(distance) == null) {
                journeyAdapter.add(newPoint);
                milestones.remove(selected);
                milestoneAdapter.notifyDataSetChanged();
            } else {
                //Maybe notify the user that another milestone there
                AlertDialog.Builder bob = new AlertDialog.Builder(this);
                bob.setTitle("Could not create Journey")
                        .setMessage("There is already a milestone set for that distance." +
                                "\nTry a different distance or remove the current milestone" +
                                " associated with that distance.");
                bob.setPositiveButton("Ok", (dialogInterface, i) -> {
                });
                bob.show();
            }
        }
    }

    public void save(View view) {
        Intent result = new Intent();
        ArrayList<Journey> journeys = journeyAdapter.getJourneys();
//        challenge.cleanJourneys();
//        for (Journey j : journeyAdapter.getJourneys()) {
//            challenge.addJourney(newG);
//        }
//        result.putExtra("challenge", challenge);
        result.putExtra("journeys", journeys);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (TrackYourTrek.areThereChanges) {
            TrackYourTrek.saveToDataBase();
        }
    }
}
