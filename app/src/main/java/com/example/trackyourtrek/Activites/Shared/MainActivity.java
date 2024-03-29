package com.example.trackyourtrek.Activites.Shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.Activites.Admin.Admin;
import com.example.trackyourtrek.Activites.Walker.walker;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.TrekAdmin;
import com.example.trackyourtrek.System.Collections.Items.TrekUser;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private TextView edtUsername, edtPassword;
    private final String ipAddress = "http://165.255.148.112:8080/";
    private static final Stack stack = new Stack();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        Walker dummie = new Walker("coolguy69","123456","Kade","Duffin","aaron.elistam@hotlegsfreeemail.co.za");
        TrackYourTrek.getInstance().register(dummie);
    }

    public void btnLoginClick(View view) {
        //String username = edtUsername.getText().toString();
        //String password = edtPassword.getText().toString();
        //new LoginActivity(this, ipAddress).execute(username, password);
        //Where Log In Code goes
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        String username =edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        TrekUser trekUser = TrackYourTrek.getInstance().logIn(username,password);
        if(trekUser instanceof TrekAdmin){
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
        }else if(trekUser instanceof Walker){
            Intent intent = new Intent(this, walker.class);
            intent.putExtra("walker",(Walker)trekUser);
            startActivity(intent);
        }
        edtPassword.setText("");
        edtUsername.setText("");

    }
    public void register(View view){
        setContentView(R.layout.register);
        stack.push(R.layout.log_in);
    }
    public void registerUser(View view){
        String fname,lname,username,email,password;
        fname=((TextView)findViewById(R.id.edtFirstName)).getText().toString();
        lname=((TextView)findViewById(R.id.edtLastName)).getText().toString();
        username=((TextView)findViewById(R.id.edtUsername)).getText().toString();
        email=((TextView)findViewById(R.id.edtEmail)).getText().toString();
        password=((TextView)findViewById(R.id.edtPassword3)).getText().toString();
        Walker walker = new Walker(username,password,fname,lname,email);
        TrackYourTrek.getInstance().register(walker);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(stack.empty())
        super.onBackPressed();
        else
        setContentView((int)stack.pop());
    }
}
