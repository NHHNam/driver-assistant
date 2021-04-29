package com.example.driverassistant.Petrol;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.R;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    private EditText edname;
    private EditText edaddress;
    private EditText eddate;
    private EditText edtime;
    private Button btnUpdate;

    // variables for Date picker
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth = -1;

    // variables for Time picker
    private int lastSelectedHour = -1;
    private int lastSelectedMinute;

    private Event eventer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petrol_activity_update);

        initEvent();

        eventer = (Event) getIntent().getExtras().get("object_event");
        if(eventer != null){
            edname.setText(eventer.getName());
            edaddress.setText(eventer.getAddress());
            eddate.setText(eventer.getDate());
            edtime.setText(eventer.getTime());
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEvent();
            }
        });

        eddate.setOnClickListener(v -> {
            if (lastSelectedDayOfMonth == -1) {
                // set tomorrow day by default
                final Calendar c = Calendar.getInstance();
                this.lastSelectedYear = c.get(Calendar.YEAR);
                this.lastSelectedMonth = c.get(Calendar.MONTH);
                this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH) + 1;
            }

            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eddate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    lastSelectedYear = year;
                    lastSelectedMonth = month;
                    lastSelectedDayOfMonth = dayOfMonth;
                }
            };

            DatePickerDialog datePickerDialog =  new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

            // set min date is tomorrow
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 24*60*60*1000);

            datePickerDialog.show();
        });

        edtime.setOnClickListener(v -> {
            if(this.lastSelectedHour == -1)  {
                // set next hour of current time
                final Calendar c = Calendar.getInstance();
                this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY) + 1;
                this.lastSelectedMinute = c.get(Calendar.MINUTE);
            }

            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // format to 04:04 if hour of minute < 10
                    edtime.setText(( (hourOfDay < 10) ? "0" + hourOfDay : hourOfDay ) + ":" + ( (minute < 10) ? "0" + minute : minute ) );
                    lastSelectedHour = hourOfDay;
                    lastSelectedMinute = minute;
                }
            };

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);

            timePickerDialog.show();
        });
    }

    private void updateEvent() {
        String strname = edname.getText().toString().trim();
        String straddress = edaddress.getText().toString().trim();
        String strdate = eddate.getText().toString().trim();
        String strtime = edtime.getText().toString().trim();

        if(TextUtils.isEmpty(strname) || TextUtils.isEmpty(straddress) || TextUtils.isEmpty(strdate) || TextUtils.isEmpty(strtime)){
            return;
        }

        //update user
        eventer.setName(strname);
        eventer.setAddress(straddress);
        eventer.setDate(strdate);
        eventer.setTime(strtime);

        EventDatabase.getInstance(this).eventDao().updateEvent(eventer);
        Toast.makeText(this,"Event update successfully",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void initEvent() {
        edname  = findViewById(R.id.update_name);
        edaddress = findViewById(R.id.update_address);
        eddate = findViewById(R.id.update_date);
        edtime = findViewById(R.id.update_time);
        btnUpdate = findViewById(R.id.btn_update_user);
    }
}