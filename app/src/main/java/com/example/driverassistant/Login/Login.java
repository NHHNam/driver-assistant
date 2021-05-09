package com.example.driverassistant.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.driverassistant.Home.History.History;
import com.example.driverassistant.R;
import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {
    private TextInputLayout username;
    private TextInputLayout pwd;
    private Button login;
    private TextView sign_up;
    private TextView tvError;

    private AccountDatabase db;
    private AccountDAO dao;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);

        bindUIControls();

        // room database
        db = AccountDatabase.getInstance(this);
        dao = db.accountDAO();

        sp = getSharedPreferences("login", MODE_PRIVATE);

        if (sp.getBoolean("logged",false)) {
            String spUsername = sp.getString("username","");

            Intent intent = new Intent(Login.this, History.class);
            intent.putExtra("username", spUsername);
            startActivity(intent);

            finish();
            return;
        }

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
                Intent intent = new Intent(Login.this, History.class);
                intent.putExtra("username", name);
                startActivity(intent);

                sp.edit().putBoolean("logged", true).apply();
                sp.edit().putString("username", name).apply();

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
        tvError.setVisibility(View.GONE);

        username.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                tvError.setVisibility(View.GONE);
        });

        pwd.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                tvError.setVisibility(View.GONE);
        });
    }
}