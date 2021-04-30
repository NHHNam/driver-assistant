package com.example.driverassistant.chucnang;

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

public class loaithunhap extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ChucNang> mlist;
    private Button btn_add;
    private chucNangAdapter adapter;
    private EditText ed_loai_thu_nhap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loaithunhap);
        //anh xa
        recyclerView = findViewById(R.id.rCV_loai_thu_nhap);
        btn_add = findViewById(R.id.btn_add_loai_thu_nhap);
        ed_loai_thu_nhap = findViewById(R.id.ed_loai_thu_nhap);

        mlist = new ArrayList<>();
        mlist.add(new ChucNang("Bán xe"));
        mlist.add(new ChucNang("Hoàn tiền"));
        mlist.add(new ChucNang("Lái xe"));
        mlist.add(new ChucNang("Vận chuyển hàng hoá"));

        adapter = new chucNangAdapter(mlist,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_loai_chi();
            }
        });

    }

    private void add_loai_chi() {
        String name = ed_loai_thu_nhap.getText().toString().trim();
        mlist.add(new ChucNang(name));
        adapter.notifyDataSetChanged();
    }
}
