package com.example.trackyourtrek.Activites.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;

public class EditMilestoneActivity extends AppCompatActivity {
    private TextView edtId, editFact, edtLocation;
    private Milestone milestone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_milestone);
        if (getIntent() != null) {
            Intent intent = getIntent();
            milestone = (Milestone) intent.getSerializableExtra("milestone");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        editFact = findViewById(R.id.edtMilestoneFact);
        edtId = findViewById(R.id.edtMilestoneID);
        edtLocation = findViewById(R.id.edtLocation);
        if (milestone == null) {
            milestone = new Milestone(0, "Location", "Fact");
        }
        editFact.setText(milestone.getFact());
        edtId.setText("" + milestone.getMilestoneID());
        edtLocation.setText(milestone.getLocation());
    }

    public void onReturnMilestoneClicked(View view) {
        // Return an answer, so create an intent to deliver the response
        Intent result = new Intent();
        result.putExtra("milestone", new Milestone(Integer.parseInt(edtId.getText().toString()),
                edtLocation.getText().toString(), editFact.getText().toString()));
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
