package com.example.trackyourtrek.System.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.Activites.Admin.Admin;
import com.example.trackyourtrek.Activites.Admin.editMilestone;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;

public class MilestoneAdapter extends RecyclerView.Adapter<MilestoneAdapter.MilestoneViewHolder> {

    /**
     * You need to define a View Holder, which will hold the data in its view for
     * a specific person.
     */
    public static class MilestoneViewHolder extends RecyclerView.ViewHolder {
        public TextView lblocation;
        public TextView lblID;
        public Milestone milestone;
        public View cardView;
        public MilestoneViewHolder(@NonNull View view) {
            super(view);
            cardView = view.getRootView();
            // Get references to commonly used Views in the layout.
            lblID = view.findViewById(R.id.lblID);
            lblocation = view.findViewById(R.id.lbllocation);
        }

        public void setMilestone(Milestone milestone) {
            this.milestone = milestone;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Admin.selectedItem=milestone;
                }
            });
            lblocation.setText(milestone.getLocation());
            lblID.setText(milestone.getMilestoneID());

            }
    }

    // The collection of data that this adapter is currently displaying.

    public MilestoneAdapter() {

    }

    @NonNull
    @Override
    public MilestoneAdapter.MilestoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method is called by Android when it needs a brand new View to display
        // a single person. The ViewHolder will hold a reference to this newly created View.

        // Inflate (create) the UI scenegraph from the layout xml resource.
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recyclerview_milestone,
                        //.inflate(R.layout.recyclerview_person_simple_details,
                        parent, false);

        // Put it into a View Holder object and return this.
        MilestoneAdapter.MilestoneViewHolder pvh = new MilestoneAdapter.MilestoneViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MilestoneAdapter.MilestoneViewHolder holder, int position) {
        // Given the View Holder and an index to the View to be used to display it,
        // fill the data item's values into the view.

        // Get the data to be displayed
        Milestone milestone = TrackYourTrek.getMilestones().get(position);

        // Fill the data from person into the view.
        holder.setMilestone(milestone);
    }


    @Override
    public int getItemCount() {
        return TrackYourTrek.getMilestones().size();
    }

    public void add(Milestone milestone) {
        // Add the person and notify the view of changes.
        TrackYourTrek.getInstance().addMilestone(milestone);
        // In this case, specify WHICH person changed.
        notifyItemChanged(TrackYourTrek.getMilestones().size() - 1);
    }

    public void remove(Object object) {
        // Remove the person.
        int i = TrackYourTrek.getMilestones().indexOf(object);
        TrackYourTrek.getMilestones().remove(object);
        // Notify view of underlying data changed.
        notifyItemRemoved(i);
    }
    public void edit(Milestone milestone, AppCompatActivity app){
        Intent intent = new Intent(app, editMilestone.class);
        intent.putExtra("milestone",milestone);
        app.startActivityForResult(intent,69);
    }
    public void replace(Milestone old, Milestone newOne){
        int i = TrackYourTrek.getMilestones().indexOf(old);
        if(i>=0){

            if(newOne!=null){
                TrackYourTrek.getMilestones().remove(i);
                TrackYourTrek.getMilestones().add(i,newOne);}
            notifyItemChanged(i);
        }
    }
}

