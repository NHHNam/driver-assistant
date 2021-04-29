package com.example.driverassistant.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.driverassistant.Petrol.list_main;
import com.example.driverassistant.R;
import com.example.driverassistant.chucnang.dichvu;
import com.example.driverassistant.chucnang.loaichi;
import com.example.driverassistant.chucnang.loaithunhap;
import com.example.driverassistant.chucnang.nhienlieu;

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

        map = findViewById(R.id.map);
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

    private void startMapActivity() {
        Uri gmmIntentUri = Uri.parse("geo:10.76833026 106.67583063");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    private void startPetrolActivity() {
        startActivity(new Intent(Home.this, list_main.class));
    }

    private void getdichvu() {
        Intent i = new Intent(main_activity.this, com.example.driverassistant.chucnang.dichvu.class);
        startActivity(i);
    }

    private void getnhienlieu() {
        Intent t = new Intent(main_activity.this, com.example.driverassistant.chucnang.nhienlieu.class);
        startActivity(t);
    }


    private void getloaichi() {
        Intent n = new Intent(main_activity.this, com.example.driverassistant.chucnang.loaichi.class);
        startActivity(n);
    }

    private void getloaithunhap() {
        Intent h = new Intent(main_activity.this, com.example.driverassistant.chucnang.loaithunhap.class);
        startActivity(h);
    }
}