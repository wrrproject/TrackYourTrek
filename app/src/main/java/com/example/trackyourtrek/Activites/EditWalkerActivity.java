package com.example.trackyourtrek.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.Walker;

public class EditWalkerActivity extends AppCompatActivity {
    private static Walker walker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_walker_details);
    }
    private static boolean loaded = false;
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if(intent!=null){
            Bundle extra = intent.getExtras();
            if(extra!=null){
                walker=(Walker) extra.getSerializable("walker");
                loadViews();
                writeToVIews();
                loaded=true;
            }
        }



    }
    private TextView edtFirstName,edtLastName,edtUsername,edtEmail,edtPassword;
    private void loadViews(){

         edtFirstName=((TextView)findViewById(R.id.edtFirstName));
         edtLastName=((TextView)findViewById(R.id.edtLastName));
         edtUsername=((TextView)findViewById(R.id.edtUsername));
         edtEmail=((TextView)findViewById(R.id.edtEmail));
         edtPassword=((TextView)findViewById(R.id.edtPassword3));
    }
    private void writeToVIews(){
        if(!loaded)
            loadViews();
        edtEmail.setText(walker.getEmail());
        edtFirstName.setText(walker.getfName());
        edtLastName.setText(walker.getlName());
        edtPassword.setText(walker.getPassword());
        edtUsername.setText(walker.getUsername());
    }

    public  void changeWalker(View view) {
        String fname,lname,username,email,password;
        fname=((TextView)findViewById(R.id.edtFirstName)).getText().toString();
        lname=((TextView)findViewById(R.id.edtLastName)).getText().toString();
        username=((TextView)findViewById(R.id.edtUsername)).getText().toString();
        email=((TextView)findViewById(R.id.edtEmail)).getText().toString();
        password=((TextView)findViewById(R.id.edtPassword3)).getText().toString();
        walker = new Walker(username,password,fname,lname,email);
        writeToVIews();

}
    public void onReturnClicked(View view) {
        // Return an answer, so create an intent to deliver the response
        Intent result = new Intent();
        result.putExtra("walker", walker);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

}
