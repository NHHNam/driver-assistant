package com.example.driverassistant.Function;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driverassistant.R;

import java.util.ArrayList;
import java.util.List;

public class nhienlieu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ChucNang> mlist;
    private Button btn_add;
    private chucNangAdapter adapter;
    private EditText ed_nhien_lieu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_nhienlieu);

        recyclerView = findViewById(R.id.rCV_nhien_lieu);
        btn_add = findViewById(R.id.btn_add_nhien_lieu);
        ed_nhien_lieu = findViewById(R.id.ed_nhien_lieu);

        mlist = new ArrayList<>();
        mlist.add(new ChucNang("CNG"));
        mlist.add(new ChucNang("Ethanol"));
        mlist.add(new ChucNang("Dầu diesel"));
        mlist.add(new ChucNang("Khí hoá lỏng"));
        mlist.add(new ChucNang("Xăng"));
        mlist.add(new ChucNang("Xăng cao cấp"));

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
        String name = ed_nhien_lieu.getText().toString().trim();
        mlist.add(new ChucNang(name));
        adapter.notifyDataSetChanged();
    }
}
