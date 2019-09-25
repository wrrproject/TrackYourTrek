package com.example.trackyourtrek.Activites.Admin;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Adapters.ChallengeAdapter;
import com.example.trackyourtrek.System.Adapters.MilestoneAdapter;
import com.example.trackyourtrek.System.Adapters.WalkersAdapter;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

import java.io.Serializable;
import java.util.Stack;

public class AdminActivity extends AppCompatActivity {
    public static Object selectedItem;
    private View selectedView;
    private Drawable viewColor;

    public final Stack stack = new Stack();
    ////Adapter Declaration
    WalkersAdapter adapterWalkers;
    ChallengeAdapter adapterChallenge;
    MilestoneAdapter adapterMilestone;
    private String currentVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void add(View view) {
        if (currentVar.equalsIgnoreCase("Walker")) {
            //Change to new user view
            adapterWalkers.add(Walker.newInstance());
        } else if (currentVar.equalsIgnoreCase("Challenge")) {
            adapterChallenge.add(Challenge.newInstance());
        } else if (currentVar.equalsIgnoreCase("Milestone")) {
            adapterMilestone.add(Milestone.newInstance());
        }
    }

    public void edit(View view) {
        if (currentVar.equalsIgnoreCase("Walker")) {
            if (selectedItem != null)
                adapterWalkers.edit((Walker) selectedItem, this);

        } else if (currentVar.equalsIgnoreCase("Challenge")) {
                Intent intent = new Intent(this, EditChallengeActivity.class);
                intent.putExtra("challenge", (Challenge)selectedItem);
                startActivityForResult(intent, REQ_EDIT);

        } else if (currentVar.equalsIgnoreCase("Milestone")) {
                Intent intent = new Intent(this, EditMilestoneActivity.class);
                intent.putExtra("milestone", (Milestone)selectedItem);
                startActivityForResult(intent, REQ_EDIT);
        }
    }
    public <T extends Serializable> void edit(T item, View v) {
        if (currentVar.equalsIgnoreCase("Walker")) {
            Runnable r = () -> {
                adapterWalkers.edit((Walker) selectedItem, this);
            };
            recyclerViewSelector(item, v, r);
           /* if (selectedItem != null)
                adapterWalkers.edit((Walker) selectedItem, this);*/

        } else if (currentVar.equalsIgnoreCase("Challenge")) {
            Runnable r = () -> {
                Intent intent = new Intent(this, EditChallengeActivity.class);
                intent.putExtra("challenge", item);
                startActivityForResult(intent, REQ_EDIT);
            };
            recyclerViewSelector(item, v, r);
        } else if (currentVar.equalsIgnoreCase("Milestone")) {
            Runnable r = () -> {
                Intent intent = new Intent(this, EditMilestoneActivity.class);
                intent.putExtra("milestone", item);
                startActivityForResult(intent, REQ_EDIT);
            };
            recyclerViewSelector(item, v, r);
        }
    }


    public void delete(View view) {
        if (currentVar.equalsIgnoreCase("Walker")) {
            if (selectedItem != null)
                adapterWalkers.remove(selectedItem);

        } else if (currentVar.equalsIgnoreCase("Challenge")) {
            if (selectedItem != null) {
                Runnable r = () -> adapterChallenge.remove(TrackYourTrek.getChallenges().indexOf(selectedItem));
                confirmationMessage("Challenge", r);
            } else noItemSelectedError("Challenge");
        } else if (currentVar.equalsIgnoreCase("Milestone")) {
            if (selectedItem != null) {
                Runnable r = () -> adapterMilestone.remove(TrackYourTrek.getMilestones().indexOf(selectedItem));
                confirmationMessage("Milestone", r);
            } else noItemSelectedError("Milestone");
            //adapterMilestone.remove(selectedItem);
        }
    }

    public void viewUsers(View view) {
        setContentView(R.layout.cardsviewer);
        stack.push(R.layout.activity_admin);
        TextView heading = findViewById(R.id.heading);
        heading.setText("Walker User List");
        //Intialize Adapter
        adapterWalkers = new WalkersAdapter(this, this::edit);

        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);

        // Assign adapter to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.recyclerView);
        lstPeople.setLayoutManager(layoutManager);
        lstPeople.setAdapter(adapterWalkers);

        // Set extra parameters if needed.
        // This decorator adds extra spacing around all items in the recycle view.
        lstPeople.addItemDecoration(new EqualSpaceItemDecoration(18));

        // Attach item selected event handler
        RecyclerView.OnItemTouchListener listener = new RecyclerView.SimpleOnItemTouchListener();
        lstPeople.addOnItemTouchListener(listener);

        //Set var type
        currentVar = "Walker";
    }

    public void viewChallenges(View view) {
        setContentView(R.layout.cardsviewer);
        stack.push(R.layout.activity_admin);
        TextView heading = findViewById(R.id.heading);
        heading.setText("Challenges List");

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);

        RecyclerView lst = findViewById(R.id.recyclerView);
        lst.setLayoutManager(layoutManager);
        adapterChallenge = new ChallengeAdapter(TrackYourTrek.getChallenges(), this::edit);
        lst.setAdapter(adapterChallenge);

        lst.addItemDecoration(new EqualSpaceItemDecoration(16));
        RecyclerView.OnItemTouchListener listener = new RecyclerView.SimpleOnItemTouchListener();
        lst.addOnItemTouchListener(listener);

        //Set var type
        currentVar = "Challenge";
    }

    public void viewMilestones(View view) {
        setContentView(R.layout.cardsviewer);
        stack.push(R.layout.activity_admin);
        TextView heading = findViewById(R.id.heading);
        heading.setText("Milestone List");

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);

        RecyclerView lst = findViewById(R.id.recyclerView);
        lst.setLayoutManager(layoutManager);
        adapterMilestone = new MilestoneAdapter(TrackYourTrek.getMilestones(), this::edit);
        lst.setAdapter(adapterMilestone);

        lst.addItemDecoration(new EqualSpaceItemDecoration(18));
        RecyclerView.OnItemTouchListener listener = new RecyclerView.SimpleOnItemTouchListener();
        lst.addOnItemTouchListener(listener);

        //Set var type
        currentVar = "Milestone";
    }

    public class EqualSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int mSpaceHeight;

        public EqualSpaceItemDecoration(int mSpaceHeight) {
            this.mSpaceHeight = mSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = mSpaceHeight;
            outRect.top = mSpaceHeight;
            outRect.left = mSpaceHeight;
            outRect.right = mSpaceHeight;
        }
    }

    @Override
    public void onBackPressed() {
        if (stack.empty())
            super.onBackPressed();
        else
            setContentView((int) stack.pop());
    }

    private static final int REQ_EDIT = 69;

    // Method called when the request returns with a result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQ_EDIT) && (resultCode == RESULT_OK)) {
            // the sum request intent has returned a result and the result was ok (as
            // opposed to being canceled), so do something with the result
            if (currentVar.equalsIgnoreCase("Walker")) {
                Walker newOne = (Walker) data.getExtras().getSerializable("WalkerActivity");
                if (selectedItem != null)
                    adapterWalkers.replace((Walker) selectedItem, newOne);

            } else if (currentVar.equalsIgnoreCase("Challenge")) {
                Challenge challenge = (Challenge) data.getExtras().getSerializable("challenge");
                Challenge old = ListSearches.findChallengeByID(challenge.getChallengeID());
                old.setChallenge(challenge);
                adapterChallenge.notifyItemChanged(TrackYourTrek.getChallenges().indexOf(old));
                TrackYourTrek.areThereChanges = true;
            } else if (currentVar.equalsIgnoreCase("Milestone")) {
                Milestone milestone = (Milestone) data.getExtras().getSerializable("milestone");
                Milestone old = ListSearches.findMilestoneByID(milestone.getMilestoneID());
                old.setMilestone(milestone);
                adapterMilestone.notifyItemChanged(TrackYourTrek.getMilestones().indexOf(old));
                TrackYourTrek.areThereChanges = true;
            }
        }
    }

    private <T extends Serializable> void recyclerViewSelector(T item, View v, Runnable r) {
        if (selectedItem == null) {
            selectedItem = item;
            if (v != null) {
                viewColor = v.getBackground();
                v.setBackground(new ColorDrawable(getResources().getColor(R.color.purple, null)));
                selectedView = v;
            }
        } else {
            if (selectedItem != item) {
                selectedItem = item;
                if (selectedView != null) {
                    selectedView.setBackground(viewColor);
                }
                if (v != null) {
                    viewColor = v.getBackground();
                    v.setBackground(new ColorDrawable(getResources().getColor(R.color.purple, null)));
                    selectedView = v;
                }
            } else {
                if (selectedView != null) {
                    selectedView.setBackground(viewColor);
                }
                selectedItem = null;
                viewColor = null;
                selectedView = null;
                r.run();
            }
        }
    }

    private void noItemSelectedError(String type) {
        AlertDialog.Builder bob = new AlertDialog.Builder(this);
        bob.setTitle("Error").setMessage("You must first select a " + type + " to remove.");
        bob.setPositiveButton("Ok", (dialogInterface, i) -> {
        });
        bob.show();
    }

    private void confirmationMessage(String type, Runnable runnable) {
        AlertDialog.Builder bob = new AlertDialog.Builder(this);
        bob.setTitle("Confirm Delete").setMessage("Are you sure you want to remove this " + type +
                ". This action cannot be undone.");
        bob.setPositiveButton("Confirm", (dialogInterface, i) -> {
            runnable.run();
            selectedItem = null;
        });
        bob.setNegativeButton("Cancel", (dialogInterface, i) -> {
        });
        bob.show();
    }

    public void btnLogoutClick(View view) {
        TrackYourTrek.saveToDataBase();
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (TrackYourTrek.areThereChanges) {
            TrackYourTrek.saveToDataBase();
        }
    }
}
