package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> userList;
    private EditText loginUsername, loginPassword;
    private Button mbtnSignUp, mbtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        mbtnSignUp = findViewById(R.id.btn_signup);
        mbtnLogin = findViewById(R.id.btn_login);

        mbtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpPage.class));
            }
        });

        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoggedInPage.class));
            }
        });
    }
}
