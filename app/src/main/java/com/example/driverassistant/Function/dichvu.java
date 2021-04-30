package com.example.driverassistant.Function;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driverassistant.R;

import java.util.ArrayList;
import java.util.List;

public class dichvu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ChucNang> mlist;
    private Button btn_add;
    private chucNangAdapter adapter;
    private EditText ed_dich_vu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_dichvu);

        //anh xa
        recyclerView = findViewById(R.id.rCV);
        btn_add = findViewById(R.id.btn_add_dich_vu);
        ed_dich_vu = findViewById(R.id.ed_dich_vu);

        mlist = new ArrayList<>();
        mlist.add(new ChucNang("Bơm nhiên liệu"));
        mlist.add(new ChucNang("Bộ lọc dầu"));
        mlist.add(new ChucNang("Bộ lọc khí"));
        mlist.add(new ChucNang("Bộ đánh lửa"));

        adapter = new chucNangAdapter(mlist,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

    }

    private void Add() {
        String name = ed_dich_vu.getText().toString().trim();
        mlist.add(new ChucNang(name));
        adapter.notifyDataSetChanged();
    }
}