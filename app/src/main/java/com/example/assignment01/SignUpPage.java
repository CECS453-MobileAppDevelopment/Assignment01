package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class SignUpPage extends AppCompatActivity {

    ArrayList<User> userList;
    private EditText signupUsername, signupPassword, signupPasswordCheck, signupEmail, signupPhone;
    private Button mbtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        userList = new ArrayList<>();
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupPasswordCheck = findViewById(R.id.signup_password_check);
        signupEmail = findViewById(R.id.signup_email);
        signupPhone = findViewById(R.id.signup_phone);
        mbtnRegister = findViewById(R.id.btn_register);

        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
                startActivity(new Intent(SignUpPage.this, MainActivity.class));
            }
        });
    }

    private void addUser() {
        User u = new User(signupUsername.getText().toString(), signupPassword.getText().toString(),
                signupEmail.getText().toString(), signupPhone.getText().toString());
        Toast.makeText(this, u.toString(), Toast.LENGTH_SHORT).show();
        /*
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userList);
        editor.putString("user list", json);
        editor.apply(); */
    }
}
