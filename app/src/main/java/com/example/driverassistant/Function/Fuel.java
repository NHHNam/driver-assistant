package com.example.driverassistant.Function;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.driverassistant.R;

import java.util.Calendar;

public class Fuel extends AppCompatActivity {
    private EditText etDate;
    private EditText etTime;
    private EditText etPlace;
    private EditText etPrice;
    private EditText etLit;

    // variables for Date picker
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth = -1;

    // variables for Time picker
    private int lastSelectedHour = -1;
    private int lastSelectedMinute;

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_fuel);

        setToolBar();

        bindUI();
    }

    private void bindUI() {
        etDate = findViewById(R.id.fuel_date);
        etTime = findViewById(R.id.fuel_time);
        etPlace = findViewById(R.id.fuel_place);
        etPrice = findViewById(R.id.fuel_price);
        etLit = findViewById(R.id.fuel_lit);

        etDate.setOnClickListener(v -> {
            if (lastSelectedDayOfMonth == -1) {
                final Calendar c = Calendar.getInstance();
                this.lastSelectedYear = c.get(Calendar.YEAR);
                this.lastSelectedMonth = c.get(Calendar.MONTH);
                this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            }

            DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;
            };

            DatePickerDialog datePickerDialog =  new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000);

            datePickerDialog.show();
        });

        etTime.setOnClickListener(v -> {
            if(this.lastSelectedHour == -1)  {
                final Calendar c = Calendar.getInstance();
                this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
                this.lastSelectedMinute = c.get(Calendar.MINUTE);
            }

            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // format to 04:04 if hour of minute < 10
                    etTime.setText(( (hourOfDay < 10) ? "0" + hourOfDay : hourOfDay ) + ":" + ( (minute < 10) ? "0" + minute : minute ) );
                    lastSelectedHour = hourOfDay;
                    lastSelectedMinute = minute;
                }
            };

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);

            timePickerDialog.show();
        });
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_fuel);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //========== MENU OPTION CREATE ==========//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.function_add, menu);
        return true;
    }

    //========== MENU OPTION EVENT ==========//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.function_add:

                intent = getIntent();
                intent.putExtra("Date", etDate.getText().toString());
                intent.putExtra("Time", etTime.getText().toString());
                intent.putExtra("Place", etPlace.getText().toString());
                intent.putExtra("Price", etPrice.getText().toString());
                intent.putExtra("Lit", etLit.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
