package com.example.trackyourtrek.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Milestone;

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

    @Override
    protected void onStart() {
        super.onStart();
        TextView lblName = findViewById(R.id.lblChallengeJourney);
        lblName.setText("Name: " + challenge.getName());
    }

    public void Save(View view) {
        Intent result = new Intent();
        result.putExtra("challenge", challenge);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
