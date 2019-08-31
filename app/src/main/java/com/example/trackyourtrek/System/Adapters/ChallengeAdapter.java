package com.example.trackyourtrek.System.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trackyourtrek.Activites.Admin;
import com.example.trackyourtrek.Activites.EditWalkerActivity;
import com.example.trackyourtrek.Activites.editChallenge;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    /**
     * You need to define a View Holder, which will hold the data in its view for
     * a specific person.
     */
    public static class ChallengeViewHolder extends RecyclerView.ViewHolder {
        public TextView lblName;
        public TextView lblTotalDistance;
        public Challenge challenge;
        public View cardView;
        public ChallengeViewHolder(@NonNull View view) {
            super(view);
            cardView = view.getRootView();
            // Get references to commonly used Views in the layout.
            lblName = view.findViewById(R.id.lblID);
            lblTotalDistance = view.findViewById(R.id.lbllocation);
        }

        public void setChallenge(Challenge challenge) {
            this.challenge = challenge;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Admin.selectedItem=challenge;
                }
            });
            lblName.setText("Name: "+ this.challenge.getName());
            lblTotalDistance.setText("Distance: " + this.challenge.getTotalDistance());
            if(this.challenge.isActive()){
                cardView.setBackgroundColor(Color.GREEN);
            }else{
                cardView.setBackgroundColor(Color.RED);
            }
    }
    }

    // The collection of data that this adapter is currently displaying.

    public ChallengeAdapter() {

    }

    @NonNull
    @Override
    public ChallengeAdapter.ChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method is called by Android when it needs a brand new View to display
        // a single person. The ViewHolder will hold a reference to this newly created View.

        // Inflate (create) the UI scenegraph from the layout xml resource.
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_challenge,
                        //.inflate(R.layout.recyclerview_person_simple_details,
                        parent, false);

        // Put it into a View Holder object and return this.
        ChallengeAdapter.ChallengeViewHolder pvh = new ChallengeAdapter.ChallengeViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeAdapter.ChallengeViewHolder holder, int position) {
        // Given the View Holder and an index to the View to be used to display it,
        // fill the data item's values into the view.

        // Get the data to be displayed
        Challenge person = TrackYourTrek.viewChallenges().get(position);

        // Fill the data from person into the view.
        holder.setChallenge(person);
    }


    @Override
    public int getItemCount() {
        return TrackYourTrek.viewChallenges().size();
    }

    public void add(Challenge challenge) {
        // Add the person and notify the view of changes.
        TrackYourTrek.getInstance().addChallenge(challenge);
        // In this case, specify WHICH person changed.
        notifyItemChanged(TrackYourTrek.viewChallenges().size() - 1);
    }

    public void remove(Object object) {
        // Remove the person.
        if(object instanceof Challenge){
            Challenge challenge = (Challenge)object;
        int i = TrackYourTrek.viewChallenges().indexOf(challenge);
            TrackYourTrek.viewChallenges().remove(challenge);
        // Notify view of underlying data changed.
        notifyItemRemoved(i);
        }
    }
    public  void edit(Challenge challenge, AppCompatActivity app){
        Intent intent = new Intent(app, editChallenge.class);
        intent.putExtra("challenge",challenge);
        app.startActivityForResult(intent,69);
    }
    public void replace(Challenge old, Challenge newOne){
        int i = TrackYourTrek.getChallenges().indexOf(old);
        if(i>=0){

            if(newOne!=null){
                TrackYourTrek.getChallenges().remove(i);
                TrackYourTrek.getChallenges().add(i,newOne);}
            notifyItemChanged(i);
        }
    }
}


