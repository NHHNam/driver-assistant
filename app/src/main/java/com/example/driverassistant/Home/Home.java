package com.example.driverassistant.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.driverassistant.Map.MapsActivity;
import com.example.driverassistant.Petrol.list_main;
import com.example.driverassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private TextView map;
    private TextView petrol;
    private ImageView imgmap;
    private ImageView imgpetrol;

    private TextView dichvu;
    private TextView nhienlieu;
    private ImageView imgdichvu;
    private ImageView imgnhienlieu;

    private TextView loaichi;
    private TextView loaithunhap;
    private ImageView imgloaichi;
    private ImageView imgloaithunhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_home);

        setBottomNavigation();

        map = findViewById(R.id.home_fragment_map);
        petrol = findViewById(R.id.list_petrol);
        imgmap = findViewById(R.id.inmgmap);
        imgpetrol = findViewById(R.id.imgpetrol);

        dichvu = findViewById(R.id.tvdichvu);
        nhienlieu = findViewById(R.id.tvnhienlieu);
        imgdichvu = findViewById(R.id.imgdichvu);
        imgnhienlieu = findViewById(R.id.imgnhienlieu);

        loaichi = findViewById(R.id.tvloaichi);
        loaithunhap = findViewById(R.id.tvloaithunhap);
        imgloaichi = findViewById(R.id.imgloaichi);
        imgloaithunhap = findViewById(R.id.imgloaithunhap);

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

        dichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdichvu();
            }
        });

        nhienlieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnhienlieu();
            }
        });

        imgdichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdichvu();
            }
        });

        imgnhienlieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnhienlieu();
            }
        });

        loaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getloaichi();
            }
        });

        loaithunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getloaithunhap();
            }
        });

        imgloaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getloaichi();
            }
        });

        imgloaithunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getloaithunhap();
            }
        });

    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_report);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    return true;

                case R.id.home_bottom_history:

                    return true;

                case R.id.home_bottom_map:
                    startActivity(new Intent(Home.this, MapsActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    return true;
            }

            return false;
        });
    }

    private void startMapActivity() {
        startActivity(new Intent(Home.this, MapFragment.class));
    }

    private void startPetrolActivity() {
        startActivity(new Intent(Home.this, list_main.class));
    }

    private void getdichvu() {
        Intent i = new Intent(Home.this, com.example.driverassistant.Function.dichvu.class);
        startActivity(i);
    }

    private void getnhienlieu() {
        Intent t = new Intent(Home.this, com.example.driverassistant.Function.nhienlieu.class);
        startActivity(t);
    }


    private void getloaichi() {
        Intent n = new Intent(Home.this, com.example.driverassistant.Function.loaichi.class);
        startActivity(n);
    }

    private void getloaithunhap() {
        Intent h = new Intent(Home.this, com.example.driverassistant.Function.loaithunhap.class);
        startActivity(h);
    }
}