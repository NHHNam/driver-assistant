package com.example.driverassistant.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driverassistant.Home.Home;
import com.example.driverassistant.R;


public class Login extends AppCompatActivity {
    private EditText username;
    private EditText pwd;
    private Button login;
    private TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);

        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = pwd.getText().toString().trim();

                //check username and password is empty ?
                if(name.isEmpty()){
                    Toast.makeText(Login.this,"Please enter your username !!", Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                }else if(password.isEmpty()){
                    Toast.makeText(Login.this,"Please enter your password !!", Toast.LENGTH_SHORT).show();
                    pwd.requestFocus();
                }

                if(name.equals("Admin") && password.equals("Admin")){
                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Please enter again form !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.btn_login);
        sign_up = findViewById(R.id.sign_up);
    }


}