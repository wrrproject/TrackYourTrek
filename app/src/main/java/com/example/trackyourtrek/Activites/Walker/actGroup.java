package com.example.trackyourtrek.Activites.Walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Group;
import com.example.trackyourtrek.System.Collections.Items.Milestone;
import com.example.trackyourtrek.System.TrackYourTrek;

public class actGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_group);
    }

    Spinner spinner;
    TextView edtName;
    @Override
    protected void onStart() {
        super.onStart();
        spinner=findViewById(R.id.spinnerGroups);
        ArrayAdapter<Group> groupArrayAdapter = new ArrayAdapter<Group>(this,
                android.R.layout.simple_list_item_1, // a pre-define layout with text views
                android.R.id.text1, // the id of the text view in which to display the toString value
                TrackYourTrek.getGroups()); // the person collection to be displayed
        edtName=findViewById(R.id.edtGroupName);
        spinner.setAdapter(groupArrayAdapter);
    }

    public void Save(View view) {
        Group group = (Group) spinner.getSelectedItem();
        if(group!=null) {
            Intent result = new Intent();
            result.putExtra("group",group );
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }

    public void Create(View view) {
        String name = edtName.getText().toString();
        if(name.length()>0) {
            String ID = TrackYourTrek.getGroups().size()+"";
            Group group = new Group(ID,name);
            TrackYourTrek.getInstance().createGroup(group);
            Intent result = new Intent();
            result.putExtra("group",group );
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }
}
