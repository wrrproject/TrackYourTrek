package com.example.trackyourtrek.Activites.Walker;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Adapters.ChallengeAdapter;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.ChallengeParticipation;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

public class WalkerChallenges extends AppCompatActivity {

    Walker currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker_challenges);
        if (getIntent() != null) {
            currentUser = (Walker) getIntent().getSerializableExtra("WalkerActivity");
            String username = currentUser.getUsername();
            currentUser = (Walker) ListSearches.findUserByUsername(username);
            if (currentUser == null) {
                finish();
            }
        } else {
            finish();
        }
        Challenge dummie = new Challenge(0, "Example", 100, true);
        TrackYourTrek.getInstance().addChallenge(dummie.getName(), dummie.getTotalDistance());
    }

    RecyclerView recyclerView;
    ChallengeAdapter challengeAdapter;
    Spinner spinner;

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.recycler);
        challengeAdapter = new ChallengeAdapter(ListSearches
                .findChallengesByUsername(currentUser.getUsername()), (challenge, v) -> {
            //Challenge clicked in recycler view
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(challengeAdapter);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<Challenge> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, // a pre-define layout with text views
                android.R.id.text1, // the id of the text view in which to display the toString value
                TrackYourTrek.getChallenges()); // the person collection to be displayed
        spinner.setAdapter(arrayAdapter);

    }

    public void save(View view) {
        for (Challenge chall : TrackYourTrek.getChallenges()) {
            int pos = TrackYourTrek.getChallenges().indexOf(chall);
            if (pos >= 0) {
                Challenge challenge = TrackYourTrek.getChallenges().get(pos);
                pos = TrackYourTrek.getWalkers().indexOf(currentUser);
                if (pos >= 0) {
                    Walker walker = TrackYourTrek.getWalkers().get(pos);
//                    challenge.addParticipates(walker);
//                    walker.addChallenge(challenge);
                    TrackYourTrek.getChallengeParticipation().add(new ChallengeParticipation(
                            TrackYourTrek.getChallengeParticipation().size(), walker.getUsername(),
                            challenge.getChallengeID(),
                            TrackYourTrek.stringToCalendar(TrackYourTrek.getTodayDateAsString()),
                            null, false));

                }
            }
        }
        finish();

    }

    public void pick(View view) {
        Challenge c = (Challenge) spinner.getSelectedItem();
        if (ListSearches.findParticipationByUsernameAndChallengeID(currentUser.getUsername(),
                c.getChallengeID()) == null) {
            challengeAdapter.add(c);
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
