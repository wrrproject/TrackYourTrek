package com.example.trackyourtrek.Activites.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Adapters.JourneyAdapter;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.util.List;

public class editJourney extends AppCompatActivity {
    Challenge challenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journey);
        if(getIntent()!=null){
            challenge=(Challenge) getIntent().getExtras().getSerializable("challenge");
        }
    }

    RecyclerView recyclerView ;
    TextView edtDistance;
    JourneyAdapter journeyAdapter;
    Spinner comboBox;
    @Override
    protected void onStart() {
        super.onStart();
        TextView lblName = findViewById(R.id.lblChallengeJourney);
        lblName.setText("Name: " + challenge.getName());
        recyclerView= findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager LM = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(LM);
        journeyAdapter = new JourneyAdapter(challenge.getJourney());
        recyclerView.setAdapter(journeyAdapter);
        //Set spinner milestones
        // Get data to be displayed.
        List<Milestone> milestones = TrackYourTrek.getMilestones();

        // Get an adapter to display a person in the ListView.
        // Going to use one with a predefined layout that uses the toString method
        // of the data item.
        ArrayAdapter<Milestone> adapter = new ArrayAdapter<Milestone>(this,
                android.R.layout.simple_list_item_1, // a pre-define layout with text views
                android.R.id.text1, // the id of the text view in which to display the toString value
                milestones); // the person collection to be displayed
        comboBox = findViewById(R.id.ComboMilestones);
        comboBox.setAdapter(adapter);
        edtDistance = findViewById(R.id.edtDistance);

    }
    public void Add(View view) {
            int milestonePlace = comboBox.getSelectedItemPosition();
        if (milestonePlace >= 0) {
            Milestone selected = TrackYourTrek.getMilestones().get(milestonePlace);
            Journey newPoint = new Journey();
            newPoint.setChallenge(challenge);
            newPoint.setMilestone(selected);
            int distance=Integer.parseInt(edtDistance.getText().toString());
            newPoint.setDistance(distance);
            if(challenge.findByDistance(distance)==null && journeyAdapter.findByDisatcne(distance)==null){
            journeyAdapter.add(newPoint);
            journeyAdapter.notifyItemInserted(journeyAdapter.getItemCount()-1);
            }
            else
            {
                //Maybe notify the user that another milsetone there

            }
    }
    }
    public void Save(View view) {
        Intent result = new Intent();
        challenge.cleanJourneys();
        for (Journey newG:journeyAdapter.getJourneys()) {
            challenge.addJourney(newG);
        }
        result.putExtra("challenge", challenge);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
