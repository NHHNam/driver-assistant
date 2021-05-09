package com.example.driverassistant.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.driverassistant.Home.Welcome;
import com.example.driverassistant.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private TextInputLayout etUsername;
    private TextInputLayout etPass;
    private TextInputLayout etConfirmPass;
    private TextInputLayout etEmail;
    private Button btnRegister;
    private TextView tvReturn;
    private TextView tvSuccess;

    private AccountDatabase db;
    private AccountDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);

        bindUIControls();

        // room database
        db = AccountDatabase.getInstance(this);
        dao = db.accountDAO();

    }

    private void bindUIControls() {
        etUsername = findViewById(R.id.register_username);
        etPass = findViewById(R.id.register_pwd);
        etConfirmPass = findViewById(R.id.register_confirm_pwd);
        etEmail = findViewById(R.id.register_email);
        btnRegister = findViewById(R.id.btn_register);
        tvReturn = findViewById(R.id.register_back);
        tvSuccess = findViewById(R.id.tv_success);

        tvSuccess.setVisibility(View.GONE);

        etUsername.getEditText().setOnClickListener(v -> {
            etUsername.setError(null);
            etUsername.setErrorEnabled(false);
        });

        etPass.getEditText().setOnClickListener(v -> {
            etPass.setError(null);
            etPass.setErrorEnabled(false);
        });

        etConfirmPass.getEditText().setOnClickListener(v -> {
            etConfirmPass.setError(null);
            etConfirmPass.setErrorEnabled(false);
        });

        etEmail.getEditText().setOnClickListener(v -> {
            etEmail.setError(null);
            etEmail.setErrorEnabled(false);
        });

        tvReturn.setOnClickListener(v -> {
            startActivity(new Intent(Register.this, Login.class));
            finish();
        });

        btnRegister.setOnClickListener(v -> {
            registerUser(v);
        });
    }

    private void registerUser(View v) {
        String username = etUsername.getEditText().getText().toString();
        String pass = etPass.getEditText().getText().toString();
        String confirmPass = etConfirmPass.getEditText().getText().toString();
        String email = etEmail.getEditText().getText().toString();

        if (validateUsername(username) && validatePassword(pass)
                && validateConfirmPassword(confirmPass, pass) && validateEmail(email)) {

            dao.addAccount(new Account(username, pass, email));
            tvSuccess.setVisibility(View.VISIBLE);

            new Handler().postDelayed(() -> {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }, 1500);
        }
    }

    private boolean validateUsername(String username) {
        String noWhiteSpace = "\\\\A\\\\w{4,20}\\\\z";

        if (username.isEmpty()) {
            etUsername.setError("Username cannot be empty");
            etUsername.requestFocus();
            return false;

        } else if (username.matches(noWhiteSpace)) {
            etUsername.setError("Username cannot contain white space");
            etUsername.requestFocus();
            return false;
        }

        etUsername.setError(null);
        etUsername.setErrorEnabled(false);
        return true;
    }

    private boolean validateEmail(String email) {
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
            return false;

        } else if (!email.matches(emailPattern)) {
            etEmail.setError("Email is invalid");
            etEmail.requestFocus();
            return false;
        }

        etEmail.setError(null);
        etEmail.setErrorEnabled(false);
        return true;
    }

    private boolean validatePassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$");

        if (password.isEmpty()) {
            etPass.setError("Password cannot be empty");
            etPass.requestFocus();
            return false;

        } else if (password.length() <= 6) {
            etPass.setError("Password must contain at least 6 characters");
            etPass.requestFocus();
            return false;

        } else if (!passwordPattern.matcher(password).matches()) {
            etPass.setError("Password must contain at least 1 digit and 1 letter");
            etPass.requestFocus();
            return false;
        }

        etPass.setError(null);
        etPass.setErrorEnabled(false);
        return true;
    }

    private boolean validateConfirmPassword(String confirmPassword, String password) {
        if (confirmPassword.isEmpty()) {
            etConfirmPass.setError("Confirm password cannot be empty");
            etConfirmPass.requestFocus();
            return false;

        } else if (!confirmPassword.equals(password)) {
            etConfirmPass.setError("Confirm password does not match password");
            etConfirmPass.requestFocus();
            return false;
        }

        etConfirmPass.setError(null);
        etConfirmPass.setErrorEnabled(false);
        return true;
    }
}