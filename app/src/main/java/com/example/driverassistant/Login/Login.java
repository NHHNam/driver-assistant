package com.example.driverassistant.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driverassistant.Home.Home;
import com.example.driverassistant.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {
    private TextInputLayout username;
    private TextInputLayout pwd;
    private Button login;
    private TextView sign_up;
    private TextView tvError;

    private AccountDatabase db;
    private AccountDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);

        bindUIControls();

        // room database
        db = AccountDatabase.getInstance(this);
        dao = db.accountDAO();

        login.setOnClickListener(v -> {
            String name = username.getEditText().getText().toString().trim();
            String password = pwd.getEditText().getText().toString().trim();

            // check if username and password is empty
            if (name.isEmpty()) {
                username.setError("Username cannot be empty");
                username.requestFocus();
            } else if (password.isEmpty()) {
                pwd.setError("Password cannot be empty");
                pwd.requestFocus();
            }

            username.setError(null);
            pwd.setError(null);

            // check account in Room database
            if (dao.verifyAccount(name, password) != null) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                finish();
            } else {
                tvError.setVisibility(View.VISIBLE);
            }
        });

        sign_up.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    private void bindUIControls() {
        username = findViewById(R.id.et_user);
        pwd = findViewById(R.id.et_pass);
        login = findViewById(R.id.btn_login);
        sign_up = findViewById(R.id.sign_up);
        tvError = findViewById(R.id.tv_login_error);

        // hide error message
        tvError.setVisibility(View.INVISIBLE);

        username.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                tvError.setVisibility(View.INVISIBLE);
        });

        pwd.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                tvError.setVisibility(View.INVISIBLE);
        });
    }
}