package com.example.driverassistant.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.Function.Emergency;
import com.example.driverassistant.Function.Expense;
import com.example.driverassistant.Function.Fuel;
import com.example.driverassistant.Home.History.History;
import com.example.driverassistant.Login.Login;
import com.example.driverassistant.Map.MapActivity;
import com.example.driverassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class More extends AppCompatActivity {
    private ImageView Call;
    private TextView tvCall;
    private ImageView Map;
    private TextView tvMap;
    private Button btn_logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_more);

        Call = findViewById(R.id.img_emergency);
        tvCall = findViewById(R.id.tv_emergency);
        Map = findViewById(R.id.img_map);
        tvMap = findViewById(R.id.tv_map);
        btn_logout = findViewById(R.id.btn_logout);

        setBottomNavigation();
        // set Button Event for emergency call
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Emergency.class);
                startActivity(intent);
            }
        });

        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Emergency.class);
                startActivity(intent);
            }
        });
        // set Button Event for Map
        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(More.this, MapActivity.class);
                startActivity(intent);
            }
        });

        tvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(More.this, MapActivity.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                editor.putString("username", "");
                editor.putString("password", "");
                editor.putString("email", "");
                editor.putBoolean("logged", false);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
        });
    }

    private void setBottomNavigation() {
        View bottomNavigation = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNavigationView = bottomNavigation.findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_more);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(More.this, Report.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_history:
                    startActivity(new Intent(More.this, History.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_notification:
                    startActivity(new Intent(More.this, Notification.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
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
                            startActivity(new Intent(More.this, Fuel.class));
                            break;
                        case 1: // Sửa chữa
                            startActivity(new Intent(More.this, Expense.class));
                            break;
                    }
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
