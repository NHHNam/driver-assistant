package com.example.driverassistant.mainassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.driverassistant.ListPetrol.list_main;
import com.example.driverassistant.R;
import com.example.driverassistant.map.map;

public class main_activity extends AppCompatActivity {
    private TextView map;
    private TextView petrol;
    private ImageView imgmap;
    private ImageView imgpetrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        init();

        clicking();

    }

    private void init() {
        map = findViewById(R.id.map);
        petrol = findViewById(R.id.list_petrol);
        imgmap = findViewById(R.id.inmgmap);
        imgpetrol = findViewById(R.id.imgpetrol);
    }

    private void clicking() {
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterMap();
            }
        });

        petrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterListPetrol();
            }
        });

        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterMap();
            }
        });

        imgpetrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterListPetrol();
            }
        });
    }

    private void EnterListPetrol() {
        Intent intent = new Intent(main_activity.this, list_main.class);
        startActivity(intent);
    }

    private void EnterMap() {
        Intent intent = new Intent(main_activity.this, com.example.driverassistant.map.map.class);
        startActivity(intent);
    }
}