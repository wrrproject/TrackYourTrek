package com.example.trackyourtrek.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Adapters.ChallengeAdapter;
import com.example.trackyourtrek.System.Adapters.WalkersAdapter;
import com.example.trackyourtrek.System.Collections.Items.Challenge;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.util.Stack;

public class Admin extends AppCompatActivity {
        public static Object selectedItem;
    private static final Stack stack = new Stack();
        ////Adapte Declaration
        WalkersAdapter adapterWalkers;
        ChallengeAdapter adapterChallenge;
        private String currentVar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void add(View view) {
        if(currentVar.equalsIgnoreCase("Walker")){
            adapterWalkers.add(Walker.newInstance());

        }else  if(currentVar.equalsIgnoreCase("Challenge")){
            adapterChallenge.add(Challenge.newInstance());

        } else if(currentVar.equalsIgnoreCase("Milstone")){

        }
    }
    public void delete(View view) {
        if(currentVar.equalsIgnoreCase("Walker")){
            if(selectedItem!=null)
            adapterWalkers.remove(selectedItem);

        }else  if(currentVar.equalsIgnoreCase("Challenge")){
            adapterChallenge.remove(selectedItem);

        } else if(currentVar.equalsIgnoreCase("Milstone")){

        }
    }

    public void viewUsers(View view) {
        setContentView(R.layout.cardsviewer);
        stack.push(R.layout.activity_admin);
        TextView heading = findViewById(R.id.heading);
        heading.setText("Walker User List");
        //Intialize Adapter
        adapterWalkers= new WalkersAdapter(TrackYourTrek.getWalkers());

        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),2);

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
        currentVar="Walker";
    }

    public void viewChallenges(View view) {
        setContentView(R.layout.cardsviewer);
        stack.push(R.layout.activity_admin);
        TextView heading = findViewById(R.id.heading);
        heading.setText("Challenges List");
        //Intialize Adapter
        adapterChallenge= new ChallengeAdapter(TrackYourTrek.getChallenges());

        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),2);

        // Assign adapter to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.recyclerView);
        lstPeople.setLayoutManager(layoutManager);
        lstPeople.setAdapter(adapterChallenge);

        // Set extra parameters if needed.
        // This decorator adds extra spacing around all items in the recycle view.
        lstPeople.addItemDecoration(new EqualSpaceItemDecoration(18));

        // Attach item selected event handler
        RecyclerView.OnItemTouchListener listener = new RecyclerView.SimpleOnItemTouchListener();
        lstPeople.addOnItemTouchListener(listener);

        //Set var type
        currentVar="Challenge";
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
        if(stack.empty())
        super.onBackPressed();
        else
            setContentView((int)stack.pop());
    }
}
