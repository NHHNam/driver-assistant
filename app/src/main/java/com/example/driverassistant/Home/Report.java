package com.example.driverassistant.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.Function.Expense;
import com.example.driverassistant.Function.Fuel;
import com.example.driverassistant.Home.History.History;
import com.example.driverassistant.Home.History.HistoryAdapter;
import com.example.driverassistant.Home.UserData.User;
import com.example.driverassistant.Home.UserData.UserDAO;
import com.example.driverassistant.Home.UserData.UserDatabase;
import com.example.driverassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Report extends AppCompatActivity {
    private String username;
    private SharedPreferences sp;

    private List<User> dataList;
    private UserDatabase db;
    private UserDAO dao;

    private int totalRepair = 0;
    private int totalGas = 0;
    private double avgRepair = 0;
    private double avgGas = 0;

    private TextView tvGasTotal;
    private TextView tvGasAvg;
    private TextView tvRepairTotal;
    private TextView tvRepairAvg;
    private TextView tvTotal;
    private TextView tvTotalAvg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_report);

        setBottomNavigation();

        bindUI();

        getUser();

        getData();

    }

    private void bindUI() {
        tvGasTotal = findViewById(R.id.tv_report_gas_total);
        tvGasAvg= findViewById(R.id.tv_report_gas_avg);
        tvRepairTotal = findViewById(R.id.tv_report_repair_total);
        tvRepairAvg = findViewById(R.id.tv_report_repair_avg);
        tvTotal = findViewById(R.id.tv_report_total);
        tvTotalAvg = findViewById(R.id.tv_report_total_avg);
    }

    private void getUser() {
        sp = getSharedPreferences("report", MODE_PRIVATE);
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

        for (int i = 0; i < dataList.size(); i++) {
            User user = dataList.get(i);

            if (user.lit.equals("")) {
                totalRepair += Integer.parseInt(user.price);
            } else {
                totalGas += Integer.parseInt(user.price);
            }
        }

        avgGas = totalGas / 30.0;
        avgRepair = totalRepair / 30.0;

        bindData();
    }

    private void bindData() {
        tvGasTotal.setText(String.format("%,2dđ", totalGas*1000));
        tvGasAvg.setText(String.format("%,.2fđ", avgGas*1000));

        tvRepairTotal.setText(String.format("%,2dđ", totalRepair*1000));
        tvRepairAvg.setText(String.format("%,.2fđ", avgRepair*1000));

        tvTotal.setText(String.format("%,2dđ", (totalGas + totalGas )*1000));
        tvTotalAvg.setText(String.format("%,.2fđ", (avgRepair + avgGas)*1000));
    }

    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_report);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    return true;

                case R.id.home_bottom_history:
                    startActivity(new Intent(Report.this, History.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(Report.this, Notification.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    startActivity(new Intent(Report.this, More.class));
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
                            startActivity(new Intent(Report.this, Fuel.class));
                            break;
                        case 1: // Sửa chữa
                            startActivity(new Intent(Report.this, Expense.class));
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
