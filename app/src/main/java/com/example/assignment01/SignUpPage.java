package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                startActivity(new Intent(SignUpPage.this, MainActivity.class));
            }
        });
    }
}
