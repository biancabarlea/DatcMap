package com.example.bianca.datcmap;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,
        View.OnClickListener {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private double longitude = 21.226632;
    private double latitude = 45.747501;
    private GoogleApiClient googleApiClient;

    private int space1 = 0;
    private int space2 = 0;
    private int space3 = 0;
    private int space4 = 0;

    public final LatLng northeast1 = new LatLng(45.747488, 21.226675);
    public final LatLng southwest1 = new LatLng(45.747447, 21.226612);

    public final LatLng northeast2 = new LatLng(45.747513, 21.226668);
    public final LatLng southwest2 = new LatLng(45.747472, 21.226605);

    public final LatLng northeast3 = new LatLng(45.747538, 21.226668);
    public final LatLng southwest3 = new LatLng(45.747497, 21.226605);

    public final LatLng northeast4 = new LatLng(45.747565, 21.226675);
    public final LatLng southwest4 = new LatLng(45.7475215, 21.226612);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // googleMapOptions.mapType(googleMap.MAP_TYPE_HYBRID)
        //    .compassEnabled(true);
        // this.getCurrentLocation();
        LatLng current = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(current).title("Marker in current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);

    }

    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        if (location != null) {
//            //Getting longitude and latitude
//            longitude = location.getLongitude();
//            latitude = location.getLatitude();
//
//
//        }
        //moving the map to location
        moveMap();
    }

    private void moveMap() {
        /**
         * Creating the latlng object to store lat, long coordinates
         * adding marker to map
         * move the camera with animation
         */
        LatLng latLng = new LatLng(latitude, longitude);

        LatLngBounds bounds1 = new LatLngBounds(southwest1, northeast1); // get a bounds
        LatLngBounds bounds2 = new LatLngBounds(southwest2, northeast2); // get a bounds
        LatLngBounds bounds3 = new LatLngBounds(southwest3, northeast3); // get a bounds
        LatLngBounds bounds4 = new LatLngBounds(southwest4, northeast4); // get a bounds

        BitmapDescriptor taken = BitmapDescriptorFactory.fromResource(R.drawable.taken);
        BitmapDescriptor free = BitmapDescriptorFactory.fromResource(R.drawable.free);
        if (space1 != 0) {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(taken)
                    .positionFromBounds(bounds1));
        } else {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(free)
                    .positionFromBounds(bounds1));
        }
        if (space2 != 0) {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(taken)
                    .positionFromBounds(bounds2));
        } else {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(free)
                    .positionFromBounds(bounds2));
        }
        if (space3 != 0) {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(taken)
                    .positionFromBounds(bounds3));
        } else {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(free)
                    .positionFromBounds(bounds3));
        }
        if (space4 != 0) {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(taken)
                    .positionFromBounds(bounds4));
        } else {
            mMap.addGroundOverlay(new GroundOverlayOptions()
                    .image(free)
                    .positionFromBounds(bounds4));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(21));
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }


    @Override
    public void onClick(View view) {
        Log.v(TAG, "view click event");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        // mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerDragStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerDrag", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        // getting the Co-ordinates
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //move to current position
        moveMap();
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerClick", Toast.LENGTH_SHORT).show();
        return true;
    }

}