package com.example.driverassistant.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
    private static final int REQUEST_CALL = 111;
    private static final int REQUEST_FINE_LOCATION= 112;
    private static final int REQUEST_COARSE_LOCATION = 113;

    private ImageView imgEmergency;
    private ImageView imgMap;
    private Button btnLogout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_more);

        imgEmergency = findViewById(R.id.img_emergency);
        imgMap = findViewById(R.id.img_map);
        btnLogout = findViewById(R.id.btn_logout);

        setBottomNavigation();

        imgEmergency.setOnClickListener(v -> {
            Intent intent = new Intent(More.this, Emergency.class);
            startActivity(intent);
        });

        imgMap.setOnClickListener(v -> {
            Intent intent = new Intent(More.this, MapActivity.class);
            startActivity(intent);
        });


        btnLogout.setOnClickListener(v -> {
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
