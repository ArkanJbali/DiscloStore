package com.app.arkan.disclostore;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Get_store_loc extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String lan="";
    String lang="";
    String loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_store_loc);
        loc=getIntent().getStringExtra("location");
        if(!loc.equals("")) {
            String[] val = loc.split(",");
            lan = val[0];
            lang = val[1];
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (!lan.equals("") && !lang.equals("")){
            // Add a marker in Sydney and move the camera
            LatLng newloc = new LatLng(Float.valueOf(lan), Float.valueOf(lang));
            mMap.addMarker(new MarkerOptions().position(newloc).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newloc));
    }

    }
}
