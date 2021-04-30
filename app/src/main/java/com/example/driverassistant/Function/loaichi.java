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

public class loaichi extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ChucNang> mlist;
    private Button btn_add;
    private chucNangAdapter adapter;
    private EditText ed_loai_chi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_loaichi);

        //anh xa
        recyclerView = findViewById(R.id.rCV_loai_chi);
        btn_add = findViewById(R.id.btn_add_loai_chi);
        ed_loai_chi = findViewById(R.id.ed_loai_chi);

        mlist = new ArrayList<>();
        mlist.add(new ChucNang("Hoàn trả"));
        mlist.add(new ChucNang("Khoản phạt"));
        mlist.add(new ChucNang("Lệ phí cầu đường"));
        mlist.add(new ChucNang("Rửa xe"));

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
        String name = ed_loai_chi.getText().toString().trim();
        mlist.add(new ChucNang(name));
        adapter.notifyDataSetChanged();
    }

}
