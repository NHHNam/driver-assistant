package com.example.driverassistant.mainassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.driverassistant.ListPetrol.list_main;
import com.example.driverassistant.R;
import com.example.driverassistant.map.MapsActivity;

public class main_activity extends AppCompatActivity {
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
        setContentView(R.layout.main_activity);

        init();

        clicking();

    }

    private void init() {
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




    private void EnterListPetrol() {
        Intent intent = new Intent(main_activity.this, list_main.class);
        startActivity(intent);
    }

    private void EnterMap() {
        Intent t = new Intent(main_activity.this, MapsActivity.class);
        startActivity(t);
    }

    private void getdichvu() {
        Intent t = new Intent(main_activity.this, com.example.driverassistant.chucnang.dichvu.class);
        startActivity(t);
    }

    private void getnhienlieu() {
        Intent t = new Intent(main_activity.this, com.example.driverassistant.chucnang.nhienlieu.class);
        startActivity(t);
    }


    private void getloaichi() {
        Intent t = new Intent(main_activity.this, com.example.driverassistant.chucnang.loaichi.class);
        startActivity(t);
    }

    private void getloaithunhap() {
        Intent h = new Intent(main_activity.this, com.example.driverassistant.chucnang.loaithunhap.class);
        startActivity(h);
    }
}