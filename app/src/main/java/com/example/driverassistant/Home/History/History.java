package com.example.driverassistant.Home.History;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driverassistant.Function.Expense;
import com.example.driverassistant.Function.Fuel;
import com.example.driverassistant.Home.More;
import com.example.driverassistant.Home.Notification;
import com.example.driverassistant.Home.Report;
import com.example.driverassistant.Home.UserData.User;
import com.example.driverassistant.Home.UserData.UserDAO;
import com.example.driverassistant.Home.UserData.UserDatabase;
import com.example.driverassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class History extends AppCompatActivity {
    private String username;
    private UserDatabase db;
    private UserDAO dao;

    private LinearLayout linearWelcome;

    private RecyclerView recyclerView;

    private EditText eKind;

    private List<User> dataList;
    private HistoryAdapter adapter;

    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_history);

        setBottomNavigation();

        bindUI();

        setRecyclerView();

        getUser();

        getData();

        eKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKindOfVehicles();
            }
        });

    }


    private void bindUI() {
        linearWelcome = findViewById(R.id.linear_welcome);
        eKind = findViewById(R.id.ed_kind);
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.rv_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void getUser() {
        sp = getSharedPreferences("history", MODE_PRIVATE);
        username = sp.getString("username","");

        if (username.equals("")) {
            Intent intent = getIntent();
            username = intent.getStringExtra("username");

            sp.edit().putString("username", username).apply();
        }
    }

    private void getData() {
        db = UserDatabase.getInstance(this);
        dao = db.userDAO();

        dataList = dao.getListHistory(username);

        adapter = new HistoryAdapter(dataList);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        if (dataList.size() > 0) {
            linearWelcome.setVisibility(View.GONE);
        } else {
            linearWelcome.setVisibility(View.VISIBLE);
        }
    }

    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_history);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    Intent intent = new Intent(History.this, Report.class);
                    intent.putExtra("username", username);
                    startActivity(intent);

                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_history:
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(History.this, Notification.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    startActivity(new Intent(History.this, More.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;
            }

            return false;
        });

        FloatingActionButton fab = bottomNavigation.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Chọn");

            String[] functions = {"Đổ xăng", "Sửa chữa"};
            builder.setItems(functions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: // Đổ xăng
                            startActivityForResult(new Intent(History.this, Fuel.class), 0);
                            break;
                        case 1: // Sửa chữa
                            startActivityForResult(new Intent(History.this, Expense.class), 1);
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        User newUser = null;
        if (requestCode == 0) { // đổ xăng
            if (resultCode == RESULT_OK) {
                String date = data.getStringExtra("Date");
                String time = data.getStringExtra("Time");
                String place = data.getStringExtra("Place");
                String price = data.getStringExtra("Price");
                String lit = data.getStringExtra("Lit");

                newUser = new User(username, date, time, place, price, lit);
            }
        } else if (requestCode == 1) { // sửa chữa
            if (resultCode == RESULT_OK) {
                String date = data.getStringExtra("Date");
                String time = data.getStringExtra("Time");
                String place = data.getStringExtra("Place");
                String price = data.getStringExtra("Price");
                String lit = "";

                newUser = new User(username, date, time, place, price, lit);
            }
        }
        
        dao.addData(newUser);
        dataList.add(newUser);

        adapter.notifyDataSetChanged();

        if (dataList.size() > 0) {
            linearWelcome.setVisibility(View.GONE);
        } else {
            linearWelcome.setVisibility(View.VISIBLE);
        }
    }

    private void getKindOfVehicles() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Select kind of vehicles");

        // generate an array of places
        final String[] places = new String[]{
                "Honda",
                "Yamaha",
                "Exciter",
                "Cub"
        };
        // set single choice
        builder.setSingleChoiceItems(places, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // get the selected item
                String selectedItem = Arrays.asList(places).get(i);

                // set selected item to edit text
                eKind.setText(selectedItem);
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eKind.setText("");
            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();
    }
}
