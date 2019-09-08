package com.example.trackyourtrek.System.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.RecyclerViewItemTouch;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {
    public static class ChallengeViewHolder extends RecyclerView.ViewHolder {
        public TextView lblName;
        public TextView lblTotalDistance;
        public View cardView;
        public Challenge challenge;

        public ChallengeViewHolder(@NonNull View view) {
            super(view);

            lblName = view.findViewById(R.id.lblUsername);
            lblTotalDistance = view.findViewById(R.id.lblEmail);
            cardView = view.findViewById(R.id.cardView);
        }

        public void setChallenge(Challenge challenge, RecyclerViewItemTouch<Challenge> listener) {
            this.challenge = challenge;

//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AdminActivity.selectedItem = challenge;
//                }
//            });

            lblName.setText("Name: " + challenge.getName());
            lblTotalDistance.setText("Distance: " + challenge.getTotalDistance());
            if (challenge.isActive()) {
                cardView.setBackgroundColor(Color.GREEN);
            } else {
                cardView.setBackgroundColor(Color.RED);
            }
            cardView.setOnClickListener(view -> listener.onItemTouch(challenge));
        }
    }

    private final ArrayList<Challenge> challenges;
    private final RecyclerViewItemTouch<Challenge> mlistener;

    public ChallengeAdapter(ArrayList<Challenge> challenges, RecyclerViewItemTouch<Challenge> listener) {
        this.challenges = challenges;
        this.mlistener = listener;
    }

    @NonNull
    @Override
    public ChallengeAdapter.ChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_challenge, parent, false);

        ChallengeViewHolder cvh = new ChallengeViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeViewHolder holder, int position) {
        Challenge challenge = challenges.get(position);
        holder.setChallenge(challenge, mlistener);
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public void add(Challenge challenge) {
        challenges.add(challenge);
        notifyItemChanged(challenges.size() - 1);
        TrackYourTrek.areThereChanges = true;
    }

    public void remove(int position) {
        challenges.remove(position);
        notifyItemRemoved(position);
        TrackYourTrek.areThereChanges = true;
    }
//    public void edit(Challenge challenge, AppCompatActivity app) {
//        Intent intent = new Intent(app, EditChallengeActivity.class);
//        intent.putExtra("challenge", challenge);
//        app.startActivityForResult(intent, 69);
//    }
//
//    public void replace(Challenge old, Challenge newOne) {
//        int i = TrackYourTrek.getChallenges().indexOf(old);
//        if (i >= 0) {
//
//            if (newOne != null) {
//                TrackYourTrek.getChallenges().remove(i);
//                TrackYourTrek.getChallenges().add(i, newOne);
//            }
//            notifyItemChanged(i);
//        }
//    }
}


