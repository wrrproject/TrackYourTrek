package com.example.trackyourtrek.Activites.Walker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Group;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

public class ActGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_group);
    }

    private Spinner spinner;
    private TextView edtName;
    private ArrayAdapter<Group> groupArrayAdapter;
    private Walker user;

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                user = (Walker) extras.getSerializable("user");
            }
        }

        spinner = findViewById(R.id.spinnerGroups);
        groupArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, // a pre-define layout with text views
                android.R.id.text1, // the id of the text view in which to display the toString value
                TrackYourTrek.getGroups()); // the person collection to be displayed
        edtName = findViewById(R.id.edtGroupName);
        spinner.setAdapter(groupArrayAdapter);
        EditText edtSearch = findViewById(R.id.edtSearch);
        edtSearch.setOnKeyListener((view, keyCode, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER ||
                    keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
                String name = edtSearch.getText().toString();
                Group g = ListSearches.findGroupByName(name);
                if (g == null) {
                    edtSearch.setHint("No group with name '" + name + "'.");
                } else {
                    spinner.setSelection(groupArrayAdapter.getPosition(g), true);
                }
                edtSearch.setText("");
                return true;
            }
            return false;
        });

        //Switch spinner to current group if already in group
        if (user != null && user.getGroupID() != -1) {
            Group g = ListSearches.findGroupByID(user.getGroupID());
            spinner.setSelection(groupArrayAdapter.getPosition(g), true);
        }
    }

    public void Save(View view) {
        Group group = (Group) spinner.getSelectedItem();
        if (group != null) {
            TrackYourTrek.areThereChanges = true;
            Intent result = new Intent();
            result.putExtra("group", group);
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }

    public void Create(View view) {
        String name = edtName.getText().toString();
        if (name.length() > 0) {
            int ID = TrackYourTrek.getGroups().size();
            Group group = new Group(ID, name);
            groupArrayAdapter.add(group);
            TrackYourTrek.areThereChanges = true;
            //TrackYourTrek.createGroup(group.getGroupName());
            Intent result = new Intent();
            result.putExtra("group", group);
            setResult(Activity.RESULT_OK, result);
            finish();
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
