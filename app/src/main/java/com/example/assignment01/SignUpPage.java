package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class SignUpPage extends AppCompatActivity {

    ArrayList<User> userList;
    private TextInputLayout signupUsername;
    private TextInputLayout signupPassword;
    private TextInputLayout signupPasswordCheck;
    private TextInputLayout signupEmail;
    private TextInputLayout signupPhone;
    private Button mbtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        loadUsers();
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupPasswordCheck = findViewById(R.id.signup_password_check);
        signupEmail = findViewById(R.id.signup_email);
        signupPhone = findViewById(R.id.signup_phone);
        mbtnRegister = findViewById(R.id.btn_register);

        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInput();

            }
        });
    }

    private boolean validateUsername() {
        String username = signupUsername.getEditText().getText().toString().trim();
        if(username.isEmpty()) {
            signupUsername.setError("Field can't be empty");
            return false;
        } else {
            signupUsername.setError(null);
            return true;
        }
    }

    //TODO: add validation for strength and count
    private boolean validatePassword() {
        String password = signupPassword.getEditText().getText().toString().trim();
        String passwordCheck = signupPasswordCheck.getEditText().getText().toString().trim();
        if(password.isEmpty()) {
            signupPassword.setError("Field can't be empty");
            signupPasswordCheck.setError(null);
            return false;
        } else if (passwordCheck.isEmpty()) {
            signupPassword.setError(null);
            signupPasswordCheck.setError("Field can't be empty");
            return false;
        } else if (!password.equals(passwordCheck)) {
            signupPasswordCheck.setError("Passwords don't match");
            signupPasswordCheck.getEditText().getText().clear();
            return false;
        } else {
            signupPassword.setError(null);
            signupPasswordCheck.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = signupEmail.getEditText().getText().toString().trim();
        if(email.isEmpty()) {
            signupEmail.setError("Field can't be empty");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Invalid email address");
            return false;
        } else {
            signupEmail.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String regexPhone = "^[0-9]+$";
        String phone = signupPhone.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            signupPhone.setError("Field can't be empty");
            return false;
        } else if (phone.length() < 10 || !phone.matches(regexPhone)) {
            signupPhone.setError("Invalid phone number");
            return false;
        } else {
            signupPhone.setError(null);
            return true;
        }
    }

    private void validateInput() {
        if(!validateUsername() | !validatePassword() | !validateEmail() | !validatePhone()) {
            return;
        }
        Toast.makeText(getApplicationContext(), "Input valid", Toast.LENGTH_SHORT).show();
        addUser();
        startActivity(new Intent(SignUpPage.this, MainActivity.class));
    }

    private void addUser() {
        User user = new User(signupUsername.getEditText().getText().toString(),
                             signupPassword.getEditText().getText().toString(),
                             signupEmail.getEditText().getText().toString(),
                             signupPhone.getEditText().getText().toString());
        userList.add(user);
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userList);
        editor.putString("user list", json);
        editor.apply();
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
}
