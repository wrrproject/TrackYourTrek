package com.example.trackyourtrek.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;

public class editChallenge extends AppCompatActivity {

    private static final int REQ_JOURNEY = 169;

Challenge challenge;
TextView edtID, edtName, edtDistance;
ToggleButton toggleButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_challenge);
        if(getIntent()!=null){
            Intent intent = getIntent();
            challenge=(Challenge) intent.getSerializableExtra("challenge");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        edtDistance=findViewById(R.id.edtDistance);
        edtDistance.setText(challenge.getTotalDistance());
        edtName=findViewById(R.id.edtChallengeName);
        edtName.setText(challenge.getName());
        edtID=findViewById(R.id.edtID);
        edtID.setText(challenge.getChallengeID());
        toggleButton=findViewById(R.id.toggleActive);
        toggleButton.setChecked(challenge.isActive());
    }

    public void onSaveClicked(View view) {
        // Return an answer, so create an intent to deliver the response
        Challenge challenger = new Challenge(edtID.getText().toString(),edtName.getText().toString(),edtDistance.getText().toString(),toggleButton.isChecked());
        Intent result = new Intent();
        result.putExtra("challenge", challenger);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void ViewJourney(View view) {
    Intent intent = new Intent(this,editJourney.class);
    intent.putExtra("challenge",challenge);
    startActivityForResult(intent,REQ_JOURNEY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((requestCode == REQ_JOURNEY) && (resultCode == RESULT_OK)) {
            Challenge updated = (Challenge)data.getExtras().getSerializable("challenge");
            challenge.setJourney(updated.getJourney());
        }}
}
