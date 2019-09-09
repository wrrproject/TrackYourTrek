package com.example.trackyourtrek.System.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourtrek.Activites.Admin.AdminActivity;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.DeleteMe;
import com.example.trackyourtrek.Utility.RecyclerViewItemTouch;

import java.util.ArrayList;

public class MilestoneAdapter extends RecyclerView.Adapter<MilestoneAdapter.MilestoneViewHolder> {
    public static class MilestoneViewHolder extends RecyclerView.ViewHolder {
        public TextView lblLocation;
        public TextView lblID;
        public Milestone milestone;
        public View cardView;

        public MilestoneViewHolder(@NonNull View view) {
            super(view);

            cardView = view.findViewById(R.id.cardView);
            lblID = view.findViewById(R.id.lblMilestoneID);
            lblLocation = view.findViewById(R.id.lblLocation);
        }

        public void setMilestone(Milestone milestone, RecyclerViewItemTouch<Milestone> listener) {
            this.milestone = milestone;
//            cardView.setOnClickListener(view -> AdminActivity.selectedItem = milestone);
            lblLocation.setText(milestone.getLocation());
            lblID.setText("" + milestone.getMilestoneID());
            cardView.setOnClickListener(view -> listener.onItemTouch(milestone, cardView));
        }
    }

    private final ArrayList<Milestone> milestones;
    private final RecyclerViewItemTouch<Milestone> mListener;

    public MilestoneAdapter(ArrayList<Milestone> milestones, RecyclerViewItemTouch<Milestone> mListener) {
        this.milestones = milestones;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MilestoneAdapter.MilestoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_milestone, parent, false);

        MilestoneAdapter.MilestoneViewHolder mvh = new MilestoneAdapter.MilestoneViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MilestoneAdapter.MilestoneViewHolder holder, int position) {
//        Milestone milestone = TrackYourTrek.getMilestones().get(position);
        Milestone m = milestones.get(position);
        holder.setMilestone(m, mListener);
    }


    @Override
    public int getItemCount() {
        return milestones.size();
    }

    public void add(Milestone milestone) {
//        TrackYourTrek.getInstance().addMilestone(milestone);
//        notifyItemChanged(TrackYourTrek.getMilestones().size() - 1);
        milestones.add(milestone);
        notifyItemChanged(milestones.size() - 1);
        TrackYourTrek.areThereChanges = true;

        AdminActivity.selectedItem = milestone;
        mListener.onItemTouch(milestone, null);
    }

    public void remove(int position) {
//        int i = TrackYourTrek.getMilestones().indexOf(object);
//        TrackYourTrek.getMilestones().remove(object);
//        notifyItemRemoved(i);
        Milestone m = milestones.get(position);
        DeleteMe deleter = new DeleteMe();
        deleter.execute("Milestone", "" + m.getMilestoneID());

        milestones.remove(position);
        notifyItemChanged(position);
        TrackYourTrek.areThereChanges = true;
    }
//    public void edit(Milestone milestone, AppCompatActivity app) {
//        Intent intent = new Intent(app, EditMilestoneActivity.class);
//        intent.putExtra("milestone", milestone);
//        app.startActivityForResult(intent, 69);
//    }
//
//    public void replace(Milestone old, Milestone newOne) {
//        int i = TrackYourTrek.getMilestones().indexOf(old);
//        if (i >= 0) {
//
//            if (newOne != null) {
//                TrackYourTrek.getMilestones().remove(i);
//                TrackYourTrek.getMilestones().add(i, newOne);
//            }
//            notifyItemChanged(i);
//        }
//    }
}

