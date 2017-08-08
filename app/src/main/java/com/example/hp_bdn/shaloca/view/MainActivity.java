package com.example.hp_bdn.shaloca.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.adapter.FragmentMainAdapter;
import com.example.hp_bdn.shaloca.adapter.ListResultSearchPlaceAdapter;
import com.example.hp_bdn.shaloca.fragment.FriendFrangment;
import com.example.hp_bdn.shaloca.fragment.MapFragment;
import com.example.hp_bdn.shaloca.fragment.MessengerFragment;
import com.github.florent37.bubbletab.BubbleTab;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_SELECT_PLACE = 10001;
    private static final int REQUEST_CODE = 1010;
    private NavigationView navigationView ;
    private  DrawerLayout drawer ;
    private ImageButton imageSearch ;
    private ViewPager viewPager ;
    private BubbleTab bubbleTab ;
    private ArrayList<Fragment> fragments;
    // init FagmentAdapter
    private FragmentMainAdapter fragmentAdapter ;
    private ListResultSearchPlaceAdapter listResultSearchPlaceAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        checkPermissions();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
         innitFragment();
        fragmentAdapter = new FragmentMainAdapter(getSupportFragmentManager(),fragments);
        imageSearch = (ImageButton) findViewById(R.id.searchView);
        initView();
        innitViewPager();
    }

    private void checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale
                    (this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            android.support.v4.app.ActivityCompat.requestPermissions(this ,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION ,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION} ,REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE :
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else {
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }

        }
    }

    private void innitFragment() {
        fragments = new ArrayList<>();
        fragments.add(MapFragment.newInstance());
        fragments.add(FriendFrangment.newInstance());
        fragments.add(MessengerFragment.newInstance());
    }

    private void initView() {
        imageSearch = (ImageButton) findViewById(R.id.searchView);
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();
                    Intent intent = new PlaceAutocomplete.IntentBuilder
                            (PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(autocompleteFilter)
                            .build(MainActivity.this);
                      startActivityForResult(intent, REQUEST_SELECT_PLACE);

                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void innitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bubbleTab = (BubbleTab) findViewById(R.id.bubbleTab);
        viewPager.setAdapter(fragmentAdapter);
        bubbleTab.setupWithViewPager(viewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if(id== R.id.search){
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myfrofile) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this , MyProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fiend) {

        } else if (id == R.id.nav_mor) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this , R.style.Theme_AppCompat_DayNight_Dialog_Alert);
            alertDialog.setMessage("Do you want exit app ?");
             alertDialog.setCancelable(true);
            alertDialog.setTitle("Notification ");
            alertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = getSharedPreferences(LoginActivity.PROFILE_USER ,MODE_PRIVATE).edit();
                    editor.putBoolean(LoginActivity.AutoLogin , false);

                    editor.commit();
                    Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alertDialog.setPositiveButton("No ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_SELECT_PLACE){
            if (resultCode == RESULT_OK) {
                viewPager.setCurrentItem(0);
                Place place = PlaceAutocomplete.getPlace(this, data);
                MapFragment mapFragment = (MapFragment) fragmentAdapter.getItem(0);
                mapFragment.showLocationSearch(place);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);

            }
        }
    }
}
