package com.example.trackyourtrek.Activites.Walker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trackyourtrek.Activites.Shared.EditWalkerActivity;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Group;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;

public class walker extends AppCompatActivity {
private final int REQ_GROUP=5;
    private final int REQ_EDITWALKER=1;
    Walker currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walker);
        if(getIntent()!=null){
            currentUser=(Walker)getIntent().getSerializableExtra("walker");
        }
    }

    TextView lblHeading,edtGroupName;
    @Override
    protected void onStart() {
        super.onStart();
        lblHeading=findViewById(R.id.lblWalkerHeading);
        lblHeading.setText("Welcome, " +currentUser.getUsername()+"!");
        edtGroupName=findViewById(R.id.edtGroupName);
        if(currentUser.getGroup()!=null){
            edtGroupName.setText(currentUser.getGroup().toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_GROUP&&resultCode==RESULT_OK){
            //Need to extract the new group
            Group group= (Group)data.getExtras().getSerializable("group");
            if(TrackYourTrek.getGroups().indexOf(group)==-1){
                TrackYourTrek.getInstance().createGroup(group);
                group.add(currentUser);
                currentUser.setGroup(group);
                edtGroupName.setText(group.toString());
            }
        } else if(requestCode==REQ_EDITWALKER&&resultCode==RESULT_OK){
            //THen they changed their details
            Walker dummie = (Walker)data.getSerializableExtra("walker");
            currentUser.setlName(dummie.getlName());
            currentUser.setfName(dummie.getfName());
            currentUser.setPassword(dummie.getPassword());
            currentUser.setEmail(dummie.getEmail());
            //they cant change their usernames
        }
    }

    public void toGroup(View view) {
        Intent intent = new Intent(this,actGroup.class);
        startActivityForResult(intent,REQ_GROUP);
    }

    public void viewDetails(View view) {
        Intent intent = new Intent(this, EditWalkerActivity.class);
        intent.putExtra("walker",currentUser);
        startActivityForResult(intent,REQ_EDITWALKER);
    }
}
