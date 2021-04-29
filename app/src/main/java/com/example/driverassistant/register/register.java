package com.example.driverassistant.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.driverassistant.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class register extends AppCompatActivity {
    private EditText name;
    private EditText date;
    private EditText Address;
    private EditText username;
    private EditText pwd;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });
    }

    private void init() {//anh xa
        name = findViewById(R.id.register_name);
        date = findViewById(R.id.register_date);
        Address = findViewById(R.id.register_address);
        username = findViewById(R.id.register_username);
        pwd = findViewById(R.id.register_pwd);
        btn = findViewById(R.id.btn_register);
    }

    private void pickDate() {//chon ngay qua date picker
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}