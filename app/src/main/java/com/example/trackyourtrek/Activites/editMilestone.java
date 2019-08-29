package com.example.trackyourtrek.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Milestone;

public class editMilestone extends AppCompatActivity {


    Milestone milestone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milestone_edit);
        if(getIntent()!=null){
            Intent intent = getIntent();
            milestone=(Milestone)intent.getSerializableExtra("milestone");
        }
    }
    TextView edtId,editFact,edtLocation;
    @Override
    protected void onStart() {
        super.onStart();
        editFact=findViewById(R.id.edtMilestoneFact);
        edtId=findViewById(R.id.edtMilestoneID);
        edtLocation=findViewById(R.id.edtLocation);
        if(milestone==null)
            milestone= new Milestone("ID","Location","Fact");
        editFact.setText(milestone.getFact());
        edtId.setText(milestone.getMilestoneID());
        edtLocation.setText(milestone.getLocation());
    }

    public void onReturnMilestoneClicked(View view) {
        // Return an answer, so create an intent to deliver the response
        Intent result = new Intent();
        result.putExtra("milestone", new Milestone(edtId.getText().toString(),edtLocation.getText().toString(),editFact.getText().toString()));
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
