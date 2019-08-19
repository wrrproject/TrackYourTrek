package com.example.trackyourtrek.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Adapters.WalkersAdapter;
import com.example.trackyourtrek.System.TrackYourTrek;

public class Admin extends AppCompatActivity {

        ////Adapte Declaration
        WalkersAdapter walkersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void onDeleteClicked(View view) {
        // Delete the last person (if there is one).
        if(walkersAdapter.getItemCount() > 0)
            walkersAdapter.remove(walkersAdapter.getItemCount() - 1);
    }

    public void onLinearManagerClicked(View view) {
        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        // Assign new layout manager to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.recyclerView);
        lstPeople.setLayoutManager(layoutManager);
    }

    public void onGridManagerClicked(View view) {
        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),2);

        // Assign new layout manager to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.recyclerView);
        lstPeople.setLayoutManager(layoutManager);
    }

    public void onStaggeredManagerClicked(View view) {
        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        // Assign new layout manager to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.recyclerView);
        lstPeople.setLayoutManager(layoutManager);
    }
    public void viewUsers(View view) {
        setContentView(R.layout.cardsviewer);
        TextView heading = findViewById(R.id.heading);
        heading.setText("Walker User List");
        //Intialize Adapter
        walkersAdapter= new WalkersAdapter(TrackYourTrek.getWalkers());

        // How will the individual items be laid out in the collection view?
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),2);

        // Assign adapter to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.recyclerView);
        lstPeople.setLayoutManager(layoutManager);
        lstPeople.setAdapter(walkersAdapter);

        // Set extra parameters if needed.
        // This decorator adds extra spacing around all items in the recycle view.
        //lstPeople.addItemDecoration(new EqualSpaceItemDecoration(18));

        // Attach item selected event handler
        RecyclerView.OnItemTouchListener listener = new RecyclerView.SimpleOnItemTouchListener();
        lstPeople.addOnItemTouchListener(listener);
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
}
