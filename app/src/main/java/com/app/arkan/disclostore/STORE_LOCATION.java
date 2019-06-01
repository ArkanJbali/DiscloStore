package com.app.arkan.disclostore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class STORE_LOCATION extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    String sl = "";
    LatLng myPosition;
    Button ok;

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    mMap.setMyLocationEnabled(true);

                    return;
                }

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__location);
        ok = (Button) findViewById(R.id.ok);
        //current = (Button) findViewById(R.id.current);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        // mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // Getting LocationManager object from System Service LOCATION_SERVICE

    }

    public void getCurrentLocation() {
Toast.makeText(getApplicationContext(),"inside getCurrent",Toast.LENGTH_LONG).show();
}


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {  @Override
        public void onMapClick(LatLng point)
        { Log.d("DEBUG","Map clicked [" + point.latitude + " / " + point.longitude + "]");
            Toast.makeText(getApplicationContext(),"Map clicked [" + point.latitude + " / " + point.longitude + "]",Toast.LENGTH_LONG).show();
            LatLng newone = new LatLng(point.latitude,   point.longitude );
            mMap.addMarker(new MarkerOptions().position(newone).title("Your Store"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newone));
            sl=point.latitude+","+point.longitude;

        } });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sl.equals("")) {
                    Intent i = new Intent(STORE_LOCATION.this, busniss_reg.class);
                    i.putExtra("location", sl);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Please pick an location",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
