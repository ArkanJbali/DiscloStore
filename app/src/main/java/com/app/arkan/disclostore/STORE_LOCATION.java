package com.app.arkan.disclostore;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class STORE_LOCATION extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
String sl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__location);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {  @Override
        public void onMapClick(LatLng point)
        { Log.d("DEBUG","Map clicked [" + point.latitude + " / " + point.longitude + "]");
            Toast.makeText(getApplicationContext(),"Map clicked [" + point.latitude + " / " + point.longitude + "]",Toast.LENGTH_LONG).show();
            LatLng newone = new LatLng(point.latitude,   point.longitude );
            mMap.addMarker(new MarkerOptions().position(newone).title("Your Store"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newone));
            sl=point.latitude+""+point.longitude;
            Intent i=new Intent(STORE_LOCATION.this,busniss_reg.class);
            i.putExtra("sl",sl);
            startActivity(i);
        } });
    }
}
