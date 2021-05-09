package com.example.driverassistant.Function;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.driverassistant.R;

public class Emergency extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private TextView tvnum;
    private ImageView img_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_emergency);

        tvnum = findViewById(R.id.tv_number);
        img_call = findViewById(R.id.img_calling);

        setToolBar();

        img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call_Emergency();
            }
        });
    }

    private void Call_Emergency() {
        String num = tvnum.getText().toString().trim();
        if(num.length() > 0){ // kiểm tra số điện thoại và kiểm tra đã cấp quyền cho app chưa
            if(ContextCompat.checkSelfPermission(Emergency.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Emergency.this, new String[]{
                        Manifest.permission.CALL_PHONE
                }, REQUEST_CALL);
            }else{
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + num));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Call_Emergency();
            }else{
                Toast.makeText(Emergency.this, "Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_emergency);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}