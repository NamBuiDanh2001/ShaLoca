package com.example.hp_bdn.shaloca.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp_bdn.shaloca.R;

import com.google.android.gms.location.places.Place;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by bui on 15-Jul-17.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback , LocationListener{
    private static final String TAG = "TAG";
    private ProgressDialog Dialog;
    private MapView mapView;
    private GoogleMap googleMap;

    // Location Curent
    private Location myLocation;


    // contructor
    public static MapFragment newInstance() {
        MapFragment MapFragment = new MapFragment();
        Bundle args = new Bundle();
        MapFragment.setArguments(args);
        return MapFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        // innit mapView
         mapView= (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        // lang nghe Map da innit xong
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Dialog = new ProgressDialog(this.getContext());
        Dialog.setTitle("Loading.....");
        Dialog.setMessage("Vui long cho ");
        Dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        Dialog.show();
        super.onStart();

    }

    public void getValueFormActivity(String s) {
        Toast.makeText(getContext(), "hay" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Dialog.dismiss();
        this.googleMap = googleMap;
        initMap();
        listenMyLocation();// lang nghe su thay doi vi tri
    }

    private void listenMyLocation() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, (LocationListener) MapFragment.this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10,  (LocationListener) MapFragment.this);
    }

    private void initMap() {
//        geocoder = new Geocoder(this);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled(true);
        int maptyle = GoogleMap.MAP_TYPE_SATELLITE;
        googleMap.setMapType(maptyle);
//        googleMap.setOnMapClickListener(this);
//        googleMap.setOnMapLongClickListener(this);
//        googleMap.setOnInfoWindowClickListener(this);
//        Location location = mMap.getMyLocation();
//        Log.i("", "initMap: "+ location.toString());

        // khoi tao myInfoAdapter va gan vao map bang phuong thuc duoi
//        myInforAdapter = new MyInforAdapter(this);
//        googleMap.setInfoWindowAdapter(myInforAdapter);


    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        String address = getLocationNameByPosition(latLng);

//        if (myMarker == null) {
            // tu dong keo view den vi tri GPS
            CameraPosition cameraPosition = new CameraPosition(latLng, 17, 0, 0);
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            //
//            myMarker = drawMarker(address, "title", BitmapDescriptorFactory.HUE_BLUE, latLng);
//        } else {
//            myMarker.setPosition(latLng);
//            myMarker.setSnippet(address);
//        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void showLocationSearch(Place place){
        place.getLatLng();
        CameraPosition cameraPosition = new CameraPosition(   place.getLatLng(), 17, 0, 0);
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }



}
