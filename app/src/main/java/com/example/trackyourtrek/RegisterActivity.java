package com.example.trackyourtrek;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    TextView edtUsername;
    TextView edtPassword;
    TextView edtFName;
    TextView edtLName;
    TextView edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtFName = findViewById(R.id.edtFName);
        edtLName = findViewById(R.id.edtLName);
        edtEmail = findViewById(R.id.edtEmail);
    }

    @SuppressLint("StaticFieldLeak")
    public void btnRegisterClicked(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String fname = edtFName.getText().toString();
        String lname = edtLName.getText().toString();
        String email = edtEmail.getText().toString();

        new AsyncTask<String, String, String>() {
            private String ip;

            {
                ip = getIntent().getStringExtra("ip");
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    String link = ip + "register.php";

                    String data = URLEncoder.encode("username", "UTF-8") + "=" +
                            URLEncoder.encode(strings[0], "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                            URLEncoder.encode(strings[1], "UTF-8");
                    data += "&" + URLEncoder.encode("fName", "UTF-8") + "=" +
                            URLEncoder.encode(strings[2], "UTF-8");
                    data += "&" + URLEncoder.encode("lName", "UTF-8") + "=" +
                            URLEncoder.encode(strings[3], "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                            URLEncoder.encode(strings[4], "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();
                    wr.close();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        sb.append("\n");
                    }
                    reader.close();
                    return sb.toString();
                } catch (Exception e) {
                    return "Exception: " + e.getMessage();
                }
            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onPostExecute(String s) {
                //Codes:
                //0 - Registration Successful
                //1 - An account with that username already exists!
                //2 - There is already an account associated with that email address!
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Registration Result").setMessage(s);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }.execute(username, password, fname, lname, email);
    }
}
