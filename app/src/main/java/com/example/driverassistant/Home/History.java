package com.example.driverassistant.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driverassistant.Map.MapsActivity;
import com.example.driverassistant.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class History extends AppCompatActivity {
    private EditText loai_xe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_history);

        loai_xe = findViewById(R.id.ed_loai_xe);

        setBottomNavigation();

        loai_xe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaixe();
            }
        });
    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_report);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(History.this,Home.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_history:

                    return true;

                case R.id.home_bottom_map:
                    startActivity(new Intent(History.this, MapsActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_more:
                    return true;
            }

            return false;
        });
    }

    private void loaixe() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set title
        builder.setTitle("Select place");

        // generate an array of places
        final String[] places = new String[]{
                "Honda",
                "HuynDai",
                "Ferrari",
                "Lamboghini"
        };

        // set single choice
        builder.setSingleChoiceItems(places, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // get the selected item
                String selectedItem = Arrays.asList(places).get(i);

                // set selected item to edit text
                loai_xe.setText(selectedItem);
            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();
    }//loai_xe dialog

}
