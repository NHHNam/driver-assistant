package com.example.driverassistant.Petrol;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.driverassistant.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class list_main extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private EditText edname;
    private EditText edaddress;
    private EditText eddate;
    private EditText edtime;
    private Button btnAddevent;
    private RecyclerView recyclerView;
    private TextView dellAll;
    private EditText edsearch;

    // variables for Date picker
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth = -1;

    // variables for Time picker
    private int lastSelectedHour = -1;
    private int lastSelectedMinute;

    private Eventapdapter adapter;
    private List<Event> mlistEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petrol_list_main);

        initUser();

        mlistEvent = new ArrayList<>();
        adapter = new Eventapdapter(new Eventapdapter.IClickItems() {
            @Override
            public void update(Event event) {
                clickUpdateEvent(event);
            }

            @Override
            public void delete(Event event) {
                clickDelevent(event);
            }
        });

        adapter.setData(mlistEvent);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        btnAddevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });

        dellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDeleteAllEvent();
            }
        });

        edsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handleSearchEvent();
                }
                return false;
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

        loadData();
    }


    private void addEvent() {
        String strusername = edname.getText().toString().trim();
        String straddress = edaddress.getText().toString().trim();
        String strdate = eddate.getText().toString().trim();
        String strtime = edtime.getText().toString().trim();

        if(TextUtils.isEmpty(strusername) || TextUtils.isEmpty(straddress)){
            return;
        }

        Event event = new Event(strusername,straddress,strdate,strtime);

        if(checkExist(event)){
            Toast.makeText(this,"User existed" ,Toast.LENGTH_SHORT).show();
            return;
        }

        EventDatabase.getInstance(this).eventDao().insertEvent(event);

        EventDatabase.getInstance(this).eventDao().insertEvent(event);
        Toast.makeText(this,"Add user succesfullly!!" ,Toast.LENGTH_SHORT).show();

        edname.setText("");
        edaddress.setText("");
        eddate.setText("");
        edtime.setText("");

        hideSortKeyboard();

        loadData();
    }

    private void loadData() {
        mlistEvent = EventDatabase.getInstance(this).eventDao().getALl();
        adapter.setData(mlistEvent);
    }

    private void hideSortKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void initUser() {
        edname = findViewById(R.id.edusername);
        edaddress = findViewById(R.id.edaddress);
        eddate = findViewById(R.id.eddate);
        edtime = findViewById(R.id.edtime);
        btnAddevent = findViewById(R.id.btn_add_event);
        recyclerView = findViewById(R.id.rcvuser);
        dellAll = findViewById(R.id.tv_delall);
        edsearch = findViewById(R.id.ed_search);
    }

    private boolean checkExist(Event user){
        List<Event> list = EventDatabase.getInstance(this).eventDao().checkEvent(user.getName());
        return list != null && !list.isEmpty();
    }

    private void clickUpdateEvent(Event user) {
        Intent intent = new Intent(list_main.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_event", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }

    private void clickDelevent(Event user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete event")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventDatabase.getInstance(list_main.this).eventDao().deleteEvent(user);
                        Toast.makeText(list_main.this,"Delete event succesfullly!!" ,Toast.LENGTH_SHORT).show();

                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void clickDeleteAllEvent() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete all event")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventDatabase.getInstance(list_main.this).eventDao().deleteAllevent();
                        Toast.makeText(list_main.this,"Delete all event succesfullly!!" ,Toast.LENGTH_SHORT).show();

                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void handleSearchEvent(){
        String strkeyWord = edsearch.getText().toString().trim();
        mlistEvent = new ArrayList<>();
        mlistEvent = EventDatabase.getInstance(this).eventDao().searchEvent(strkeyWord);
        adapter.setData(mlistEvent);
        hideSortKeyboard();
    }
}