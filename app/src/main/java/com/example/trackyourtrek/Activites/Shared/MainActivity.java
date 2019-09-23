package com.example.trackyourtrek.Activites.Shared;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.Activites.Admin.AdminActivity;
import com.example.trackyourtrek.Activites.Walker.WalkerActivity;
import com.example.trackyourtrek.R;
import com.example.trackyourtrek.System.Collections.Items.TrekAdmin;
import com.example.trackyourtrek.System.Collections.Items.TrekUser;
import com.example.trackyourtrek.System.Collections.Items.Walker;
import com.example.trackyourtrek.System.TrackYourTrek;
import com.example.trackyourtrek.Utility.ListSearches;

import java.util.Objects;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    public static boolean connected;
    private static final Stack stack = new Stack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        connected = Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE))
                .getState() == NetworkInfo.State.CONNECTED || Objects.requireNonNull(connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED;

        if (connected) {
            TrackYourTrek.loadFromDataBase();
        } else {
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Connection Failed").setMessage("Could not connect to server." +
                    "\nCheck your internet connection." +
                    "\n\nWould you like to continue in offline mode." +
                    "\nNote: Only data that was synchronised during your last connected session " +
                    "will be available.");
            bob.setPositiveButton("Continue in Offline Mode", (dialogInterface, i) -> {

            });
            bob.setNegativeButton("Retry Connection", (dialogInterface, i) -> {
                Intent intent = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                assert intent != null;
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            });
            AlertDialog dialog = bob.create();
            dialog.show();
        }
    }

    public void btnLoginClick(View view) {
        //Where Log In Code goes
        TextView edtUsername, edtPassword;
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        TrekUser trekUser = TrackYourTrek.getInstance().logIn(username, password);
        if (trekUser instanceof TrekAdmin) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        } else if (trekUser instanceof Walker) {
            Intent intent = new Intent(this, WalkerActivity.class);
            intent.putExtra("WalkerActivity", trekUser);
            startActivity(intent);
        } else {
            return;
        }
        edtUsername.setText("");
        edtPassword.setText("");
    }

    public void register(View view) {
        setContentView(R.layout.register);
        stack.push(R.layout.log_in);
        TextView edtFirstanem = ((TextView) findViewById(R.id.edtFirstName));
        edtFirstanem.setOnTouchListener((view1, motionEvent) -> {edtFirstanem.setBackgroundColor(Color.WHITE) ; return true;});
        TextView lname = ((TextView) findViewById(R.id.edtLastName));
        lname.setOnTouchListener((view1, motionEvent) -> {lname.setBackgroundColor(Color.WHITE) ; return true;});
        TextView username = ((TextView) findViewById(R.id.edtUsername));
        username.setOnTouchListener((view1, motionEvent) -> {username.setBackgroundColor(Color.WHITE) ; return true;});
        TextView email = ((TextView) findViewById(R.id.edtEmail));
        email.setOnTouchListener((view1, motionEvent) -> {email.setBackgroundColor(Color.WHITE) ; return true;});
        TextView password = ((TextView) findViewById(R.id.edtPassword));
        password.setOnTouchListener((view1, motionEvent) -> {password.setBackgroundColor(Color.WHITE) ; return true;});
        TextView retype = ((TextView) findViewById(R.id.edtReEnter));
        retype.setOnTouchListener((view1, motionEvent) -> {retype.setBackgroundColor(Color.WHITE) ; return true;});
    }

    public void registerUser(View view) {
        String fname, lname, username, email, password;
        fname = ((TextView) findViewById(R.id.edtFirstName)).getText().toString();
        lname = ((TextView) findViewById(R.id.edtLastName)).getText().toString();
        username = ((TextView) findViewById(R.id.edtUsername)).getText().toString();
        email = ((TextView) findViewById(R.id.edtEmail)).getText().toString();
        password = ((TextView) findViewById(R.id.edtPassword)).getText().toString();

        //Make this nice////////////////////////////////////////////////////////////////////////////
        boolean allGood = true;
        if (ListSearches.findUserByUsername(username) != null) {
            allGood = false;
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("An account with the username " +
                    username + " already exists.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
        }

        //Passwords don't match
        if (!password.equals(((TextView) findViewById(R.id.edtReEnter)).getText().toString())||password.length()==0) {
            allGood = false;
            ((TextView) findViewById(R.id.edtPassword)).setBackgroundColor(Color.YELLOW);
            ((TextView) findViewById(R.id.edtReEnter)).setBackgroundColor(Color.YELLOW);
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("Either you password dont match or it cotains a illegal character, being -1 or Enter Key.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
        }else{
            ((TextView) findViewById(R.id.edtPassword)).setBackgroundColor(Color.GREEN);
            ((TextView) findViewById(R.id.edtReEnter)).setBackgroundColor(Color.GREEN);
        }
        //Reserved symbols
        if (username.length()==0||username.contains("-1") || username.contains("\n")) {
            allGood = false;
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("Your username contains a illegal character, being -1,Enter Key or is empty.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
            ((TextView) findViewById(R.id.edtUsername)).setBackgroundColor(Color.YELLOW);
        }else{
            ((TextView) findViewById(R.id.edtUsername)).setBackgroundColor(Color.GREEN);
        }

        if (password.length()==0||password.contains("-1") || password.contains("\n")) {
            allGood = false;
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("Your passwords contains a illegal character, being -1 Enter Key or is empty.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
            ((TextView) findViewById(R.id.edtPassword)).setBackgroundColor(Color.YELLOW);
            ((TextView) findViewById(R.id.edtReEnter)).setBackgroundColor(Color.YELLOW);
        }else{
            ((TextView) findViewById(R.id.edtPassword)).setBackgroundColor(Color.GREEN);
            ((TextView) findViewById(R.id.edtReEnter)).setBackgroundColor(Color.GREEN);
        }

        if (fname.length()==0||fname.contains("-1") || fname.contains("\n")) {
            allGood = false;
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("Your first name contains a illegal character, being -1 Enter Key or is empty.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
            ((TextView) findViewById(R.id.edtFirstName)).setBackgroundColor(Color.YELLOW);
        }else{
            ((TextView) findViewById(R.id.edtFirstName)).setBackgroundColor(Color.GREEN);
        }

        if (lname.length()==0||lname.contains("-1") || lname.contains("\n")) {
            allGood = false;
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("Your last name contains a illegal character, being -1 Enter Key or is empty.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
            ((TextView) findViewById(R.id.edtLastName)).setBackgroundColor(Color.YELLOW);
        }else{
            ((TextView) findViewById(R.id.edtLastName)).setBackgroundColor(Color.GREEN);
        }
        if (email.length()==0||email.contains("-1") || email.contains("\n")) {
            allGood = false;
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Error").setMessage("Your email contains a illegal character, being -1 Enter Key or is empty.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
            ((TextView) findViewById(R.id.edtEmail)).setBackgroundColor(Color.YELLOW);
        }else{
            ((TextView) findViewById(R.id.edtEmail)).setBackgroundColor(Color.GREEN);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////

        if (allGood) {
            Walker walker = new Walker(username, password, fname, lname, email);
            TrackYourTrek.getInstance().register(walker);
            onBackPressed();
            AlertDialog.Builder bob = new AlertDialog.Builder(this);
            bob.setTitle("Success").setMessage("You have successfully created an account, please use you details to sign in now.");
            bob.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            bob.create().show();
        }
    }

    @Override
    public void onBackPressed() {
        if (stack.empty())
            super.onBackPressed();
        else
            setContentView((int) stack.pop());
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (TrackYourTrek.areThereChanges) {
            TrackYourTrek.saveToDataBase();
        }
    }
}
