package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> userList;
    private EditText loginUsername, loginPassword;
    private Button mbtnSignUp, mbtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadUsers();
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
                validateLogin();
                startActivity(new Intent(MainActivity.this, LoggedInPage.class));
            }
        });
    }

    private void loadUsers() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user list", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        userList = gson.fromJson(json, type);
        if (userList == null) {
            userList = new ArrayList<>();
        }
    }

    private void validateLogin() {
        for(User valid: userList) {
            Log.d("userptyptptpt", valid.toString());
        }
    }
}
