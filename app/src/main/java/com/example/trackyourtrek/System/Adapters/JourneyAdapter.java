package com.example.trackyourtrek.System.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Journey;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

import java.util.ArrayList;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder> {
    public static class JourneyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblLocation, lblDistance, lblFact;
        public Journey journey;

        public JourneyViewHolder(@NonNull View itemView) {
            super(itemView);

            lblDistance = itemView.findViewById(R.id.lblDistanceJ);
            lblFact = itemView.findViewById(R.id.lblFact);
            lblLocation = itemView.findViewById(R.id.lblLocationJ);
        }

        public void setJourney(Journey journey) {
            this.journey = journey;

            lblDistance.setText(journey.getDistance() + "");
            int milestoneID = journey.getMilestoneID();
            Milestone m = ListSearches.findMilestoneByID(milestoneID);
            lblLocation.setText(m.getLocation());
            lblFact.setText(m.getFact());
        }
    }

    private final ArrayList<Journey> journeys;

    public JourneyAdapter(ArrayList<Journey> journeys) {
        this.journeys = journeys;
    }

    @NonNull
    @Override
    public JourneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_journey, parent, false);

        JourneyViewHolder jvh = new JourneyViewHolder(view);
        return jvh;
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
        boolean added = false;
        int pos = journeys.size();
        for (int i = 0; i < journeys.size(); i++) {
            if (journey.compareTo(journeys.get(i)) <= 0) {
                journeys.add(i, journey);
                pos = i;
                added = true;
            }
        }
        if (!added) {
            journeys.add(journey);
        }
        notifyItemChanged(pos);
        TrackYourTrek.areThereChanges = true;
    }

    public void remove(int position) {
        journeys.remove(position);
        notifyItemRemoved(position);
        TrackYourTrek.areThereChanges = true;
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

//    public void setJourneys(ArrayList<Journey> journeys) {
//        this.journeys = journeys;
//    }

    public Journey findByDistance(int Distance) {
        for (Journey journey : journeys) {
            if (journey.getDistance() == Distance) {
                return journey;
            }
        }
        return null;
    }
}
