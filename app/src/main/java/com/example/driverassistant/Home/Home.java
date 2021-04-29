package com.example.driverassistant.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.driverassistant.Petrol.list_main;
import com.example.driverassistant.R;

public class Home extends AppCompatActivity {
    private TextView map;
    private TextView petrol;
    private ImageView imgmap;
    private ImageView imgpetrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_home);

        map = findViewById(R.id.map);
        petrol = findViewById(R.id.list_petrol);
        imgmap = findViewById(R.id.inmgmap);
        imgpetrol = findViewById(R.id.imgpetrol);

        map.setOnClickListener(v -> {
            startMapActivity();
        });

        petrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPetrolActivity();
            }
        });

        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMapActivity();
            }
        });

        imgpetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPetrolActivity();
            }
        });
    }

    private void startMapActivity() {
        startActivity(new Intent(Home.this, MapFragment.class));
    }

    private void startPetrolActivity() {
        startActivity(new Intent(Home.this, list_main.class));
    }
}