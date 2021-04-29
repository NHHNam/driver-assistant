package com.example.driverassistant.Home;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.driverassistant.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_map_fragment);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng tdt = new LatLng(10.7321, 106.6991); // Ton Duc Thang University
        mMap.addMarker(new MarkerOptions().position(tdt).title("Marker in Ton Duc Thang University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tdt));
    }
}