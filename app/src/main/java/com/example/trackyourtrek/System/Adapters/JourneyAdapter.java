package com.example.trackyourtrek.System.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder> {
    ArrayList<Journey> journeys;

    public JourneyAdapter(PriorityQueue<Journey> journeys) {
        this.journeys = new ArrayList<Journey>();
        Journey[] arrJ = (Journey[]) journeys.toArray();
        for (Journey j:arrJ){
            journeys.add(j);
        }
    }

    @NonNull
    @Override
    public JourneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method is called by Android when it needs a brand new View to display
        // a single person. The ViewHolder will hold a reference to this newly created View.

        // Inflate (create) the UI scenegraph from the layout xml resource.
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_journey,
                        //.inflate(R.layout.recyclerview_person_simple_details,
                        parent, false);

        // Put it into a View Holder object and return this.
        JourneyAdapter.JourneyViewHolder pvh = new JourneyAdapter.JourneyViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull JourneyViewHolder holder, int position) {
        Journey journey = journeys.get(position);
        holder.setJourney(journey);
    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }
    public void add(Journey journey) {
        // Add the person and notify the view of changes.
        TrackYourTrek.getInstance().addJourney(journey.getChallenge().getChallengeID(),journey);
        journeys.add(journey);
        // In this case, specify WHICH person changed.
        notifyItemChanged( journeys.size()- 1);
    }

    public void remove(Object object) {
        // Remove the person.
        if(object instanceof Journey){
            Journey journey = (Journey) object;
            int n = journeys.indexOf(object);
            TrackYourTrek.getInstance().removeJourney(journey.getChallenge().getChallengeID(),journey);
            // Notify view of underlying data changed.
            notifyItemRemoved(n);
        }
    }

    public static class JourneyViewHolder extends RecyclerView.ViewHolder {

        TextView lblLocation,lblDistance,lblFact;
        public JourneyViewHolder(@NonNull View itemView) {
            super(itemView);
            lblDistance=itemView.findViewById(R.id.lblDistanceJ);
            lblFact=itemView.findViewById(R.id.lblFact);
            lblLocation=itemView.findViewById(R.id.lblLocationJ);
        }
        public void setJourney(Journey journey){
            lblDistance.setText(journey.getDistance());
            lblLocation.setText(journey.getMilestone().getLocation());
            lblFact.setText(journey.getMilestone().getFact());
        }
    }


}
