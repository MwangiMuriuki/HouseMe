package com.example.houseme.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.houseme.Adapters.NavDrawerAdapter;
import com.example.houseme.Fragments.FragmentAddProperty;
import com.example.houseme.Fragments.Fragment_ForSale;
import com.example.houseme.Fragments.Fragment_Home;
import com.example.houseme.Fragments.Fragment_HotelRooms;
import com.example.houseme.Fragments.Fragment_Rental;
import com.example.houseme.Models.NavDrawerItems;
import com.example.houseme.R;
import com.example.houseme.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView list;
    private NavDrawerAdapter navDrawerAdapter;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        list = (RecyclerView) findViewById(R.id.list);

        setupNavDrawerMenu();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });

        displaySelectedScreen(R.id.home);
    }

    private void setupNavDrawerMenu() {

//        ArrayList<String> nav_item = new ArrayList<>();
//        nav_item.add("Home");
//        nav_item.add("Rentals");
//        nav_item.add("For Sale");
//        nav_item.add("Add Property");
//        nav_item.add("Logout");

        List<NavDrawerItems> navDrawerItems = new ArrayList<>();

        //Home
        NavDrawerItems home = new NavDrawerItems();
        home.setTitle(getString(R.string.home));
        home.setFragment(true);
        home.setFragmentName(new Fragment_Home());
        home.setImage_resource(R.drawable.ic_home);
        navDrawerItems.add(home);

        //Rentals Fragment
        NavDrawerItems rentals = new NavDrawerItems();
        rentals.setTitle(getString(R.string.rentals));
        rentals.setFragment(true);
        rentals.setFragmentName(new Fragment_Rental());
        rentals.setImage_resource(R.drawable.ic_rent);
        navDrawerItems.add(rentals);

        //Hotel Rooms
        NavDrawerItems hotel_rooms = new NavDrawerItems();
        hotel_rooms.setTitle(getString(R.string.hotel_rooms));
        hotel_rooms.setFragment(true);
        hotel_rooms.setFragmentName(new Fragment_HotelRooms());
        hotel_rooms.setImage_resource(R.drawable.ic_hotel);
        navDrawerItems.add(hotel_rooms);

        //For Sale
        NavDrawerItems for_sale = new NavDrawerItems();
        for_sale.setTitle(getString(R.string.for_sale));
        for_sale.setFragment(true);
        for_sale.setFragmentName(new Fragment_ForSale());
        for_sale.setImage_resource(R.drawable.ic_for_sale);
        navDrawerItems.add(for_sale);

        //Add property
        NavDrawerItems add_property = new NavDrawerItems();
        add_property.setTitle(getString(R.string.add_property));
        add_property.setFragment(true);
        add_property.setFragmentName(new FragmentAddProperty());
        add_property.setImage_resource(R.drawable.ic_addpropertyicon);
        navDrawerItems.add(add_property);

        // Leads
        NavDrawerItems secondLevel = new NavDrawerItems();
        secondLevel.setTitle(getString(R.string.second_level_menu));
        secondLevel.setImage_resource(R.drawable.ic_for_sale);
        List<NavDrawerItems> secondLevelSubmenus = new ArrayList<>();

                // Leads OverView
                NavDrawerItems twoBedroom = new NavDrawerItems();
                twoBedroom.setTitle(getString(R.string.two_bedroom));
                twoBedroom.setFragment(true);
                twoBedroom.setFragmentName(new Fragment_ForSale());
                secondLevelSubmenus.add(twoBedroom);

                // All Leads
                NavDrawerItems threeBedroom = new NavDrawerItems();
                threeBedroom.setTitle(getString(R.string.three_bedroom));
                threeBedroom.setFragment(true);
                threeBedroom.setFragmentName(new Fragment_ForSale());
                secondLevelSubmenus.add(threeBedroom);

                // All Leads
                NavDrawerItems fourBedroom = new NavDrawerItems();
                fourBedroom.setTitle(getString(R.string.four_bedroom));
                fourBedroom.setFragment(true);
                fourBedroom.setFragmentName(new Fragment_ForSale());
                secondLevelSubmenus.add(fourBedroom);

        secondLevel.setSubMenus(secondLevelSubmenus);
        navDrawerItems.add(secondLevel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        navDrawerAdapter = new NavDrawerAdapter(MainActivity.this, navDrawerItems);
        list.setAdapter(navDrawerAdapter);


        /*LINK THE NAV DRAWER ITEMS TO FRAGMENTS AND ACTIVITIES*/
        navDrawerAdapter.setDrawerListener(new NavDrawerAdapter.NavDrawerListener() {
            @Override
            public void OnNavMenuItemSelected(NavDrawerItems navDrawerItems) {

                if (navDrawerItems!=null){

                    Fragment fragment;

                    if (navDrawerItems.getTitle().equals(getString(R.string.home))){
                        fragment = navDrawerItems.getFragmentName();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }

                    if (navDrawerItems.getTitle().equals(getString(R.string.rentals))){
                        fragment = navDrawerItems.getFragmentName();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }

                    if (navDrawerItems.getTitle().equals(getString(R.string.hotel_rooms))){
                        fragment = navDrawerItems.getFragmentName();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }

                    if (navDrawerItems.getTitle().equals(getString(R.string.for_sale))){
                        fragment = navDrawerItems.getFragmentName();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }

                    if (navDrawerItems.getTitle().equals(getString(R.string.add_property))){
                        fragment = navDrawerItems.getFragmentName();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.home) {
//            // Handle the camera action
//        } else if (id == R.id.rental) {
//
//        } else if (id == R.id.forSale) {
//
//        } else if (id == R.id.hotels) {
//
//        }else if (id == R.id.logout) {
//
//        }

        displaySelectedScreen(item.getItemId());
        return true;

    }

    private void displaySelectedScreen(int itemId){

        Fragment fragment = null;

        switch(itemId){
            case R.id.home:
                fragment = new Fragment_Home();
                break;
            case R.id.rental:
                fragment = new Fragment_Rental();
                break;
            case R.id.forSale:
                fragment = new Fragment_ForSale();
                break;
            case R.id.hotels:
                fragment = new Fragment_HotelRooms();
                break;
            case R.id.addProperty:
                fragment = new FragmentAddProperty();
                break;
            case R.id.logout:
                Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(logout);
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}
