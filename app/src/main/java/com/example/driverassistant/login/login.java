package com.example.driverassistant.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driverassistant.R;
import com.example.driverassistant.mainassistant.main_activity;
import com.example.driverassistant.register.register;

public class login extends AppCompatActivity {
    private EditText username;
    private EditText pwd;
    private Button login;
    private TextView sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = pwd.getText().toString().trim();

                //check username and password is empty ?
                if(name.isEmpty()){
                    Toast.makeText(login.this,"Please enter your username !!", Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                }else if(password.isEmpty()){
                    Toast.makeText(login.this,"Please enter your password !!", Toast.LENGTH_SHORT).show();
                    pwd.requestFocus();
                }

                if(name.equals("Admin") && password.equals("Admin")){
                    Intent intent = new Intent(login.this, main_activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this,"Please enter again form !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
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