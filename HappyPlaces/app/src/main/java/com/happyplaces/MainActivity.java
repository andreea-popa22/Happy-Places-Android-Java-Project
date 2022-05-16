package com.happyplaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.happyplaces.Course.LoacationAdapter;
import com.happyplaces.Course.LocationModal;

import java.util.ArrayList;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationRV = findViewById(R.id.idRVLocations);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        buildRecyclerView();
    }
    // creating variables for
    // our ui components.
    private RecyclerView locationRV;

    // variable for our adapter
    // class and array list
    private LoacationAdapter adapter;
    private ArrayList<LocationModal> locationModalArrayList;


    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<LocationModal> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (LocationModal item : locationModalArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getLocationName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        locationModalArrayList = new ArrayList<>();

        // below line is to add data to our array list.
        locationModalArrayList.add(new LocationModal("Roma", "Italy capital and good place for eating pizza"));
        locationModalArrayList.add(new LocationModal("Paris", "City of love"));
        locationModalArrayList.add(new LocationModal("Brussels", "Belgium's treasure"));
        locationModalArrayList.add(new LocationModal("Berlin", "Nice place for beer"));
        locationModalArrayList.add(new LocationModal("London", "Nice city"));
        locationModalArrayList.add(new LocationModal("Bucharest", "Beautiful but crowded"));
        locationModalArrayList.add(new LocationModal("Istanbul", "Did someone say kebab?"));
        locationModalArrayList.add(new LocationModal("Amsterdam", "So many channels"));
        locationModalArrayList.add(new LocationModal("Athens", "One of the most historical cities"));
        locationModalArrayList.add(new LocationModal("Barcelona", "Everything for everyone"));

        // initializing our adapter class.
        adapter = new LoacationAdapter(locationModalArrayList, MainActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        locationRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        locationRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        locationRV.setAdapter(adapter);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //NavHostFragment navHost = NavHostFragment.create(R.navigation.navigation);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}