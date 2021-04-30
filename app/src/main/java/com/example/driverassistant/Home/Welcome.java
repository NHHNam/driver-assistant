package com.example.driverassistant.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.Login.Login;
import com.example.driverassistant.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_welcome);

        // welcome screen for 3s
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Welcome.this, Login.class));
            finish();
        }, 3000);
    }
}
