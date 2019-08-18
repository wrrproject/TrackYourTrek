package com.example.trackyourtrek.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourtrek.R;

public class MainActivity extends AppCompatActivity {
    private TextView edtUsername, edtPassword;
    private final String ipAddress = "http://165.255.148.112:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void btnLoginClick(View view) {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        new LoginActivity(this, ipAddress).execute(username, password);
    }

    public void lblRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("ip", ipAddress);
        startActivity(intent);
    }
}
