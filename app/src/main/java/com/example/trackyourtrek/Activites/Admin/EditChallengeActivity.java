package com.example.trackyourtrek.Activites.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

import java.util.ArrayList;

public class EditChallengeActivity extends AppCompatActivity {

    private static final int REQ_JOURNEY = 169;

    private Challenge challenge;
    private TextView edtName;
    private TextView edtDistance;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_challenge);

        if (getIntent() != null) {
            Intent intent = getIntent();
            challenge = (Challenge) intent.getSerializableExtra("challenge");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        edtDistance = findViewById(R.id.edtDistance);
        edtDistance.setText("" + challenge.getTotalDistance());
        edtName = findViewById(R.id.edtChallengeName);
        edtName.setText(challenge.getName());
        TextView edtID = findViewById(R.id.edtID);
        edtID.setText("" + challenge.getChallengeID());
        toggleButton = findViewById(R.id.toggleActive);
        toggleButton.setChecked(challenge.isActive());
    }

    public void onSaveClicked(View view) {
        // Return an answer, so create an intent to deliver the response
        Challenge challenger = new Challenge(0, edtName.getText().toString(),
                Integer.parseInt(edtDistance.getText().toString()), toggleButton.isChecked());
        challenge.setChallenge(challenger);
        Intent result = new Intent();
        result.putExtra("challenge", challenge);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void viewJourney(View view) {
        Intent intent = new Intent(this, EditJourneyActivity.class);
        intent.putExtra("challenge", challenge);
        startActivityForResult(intent, REQ_JOURNEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQ_JOURNEY) && (resultCode == RESULT_OK)) {
            ArrayList<Journey> journeys = (ArrayList<Journey>) data.getSerializableExtra("journeys");
            for (Journey j : journeys) {
                if (ListSearches.findJourneyByID(j.getChallengeID(), j.getMilestoneID()) == null) {
                    TrackYourTrek.getJourneys().add(j);
                }
            }
            TrackYourTrek.areThereChanges = true;
//            Challenge updated = (Challenge) data.getExtras().getSerializable("challenge");
//            challenge.setJourney(updated.getJourney());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (TrackYourTrek.areThereChanges) {
            TrackYourTrek.saveToDataBase();
        }
    }
}
