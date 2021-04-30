package com.example.driverassistant.Home;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.example.driverassistant.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapFragment extends FragmentActivity implements OnMapReadyCallback {
    private static final int REQUEST_MAP = 111;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_map_fragment);

        ActivityCompat.requestPermissions(MapFragment.this,
                new String[] {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_MAP);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.home_fragment_map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);

        setBottomNavigation();
    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_nav);

        bottomNavigationView.setSelectedItemId(R.id.home_bottom_map);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_bottom_report:
                    startActivity(new Intent(MapFragment.this, Home.class));
                    overridePendingTransition(0,0);
                    finish();
                    return true;

                case R.id.home_bottom_history:

                    return true;

                case R.id.home_bottom_map:

                    return true;

                case R.id.home_bottom_more:
                    return true;
            }

            return false;
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng tdt = new LatLng(10.7321, 106.6991); // Ton Duc Thang University
        mMap.addMarker(new MarkerOptions().position(tdt).title("Marker in Ton Duc Thang University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tdt));
    }
}