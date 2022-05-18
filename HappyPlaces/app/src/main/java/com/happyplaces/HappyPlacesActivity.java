package com.happyplaces;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.happyplaces.RecyclerView.LocationAdapter;
import com.happyplaces.RecyclerView.LocationModel;

import java.util.ArrayList;

public class HappyPlacesActivity extends AppCompatActivity {
    private RecyclerView locationRV;
    private LocationAdapter adapter;
    private ArrayList<LocationModel> locationModalArrayList;
    private Toolbar toolbar;
    public DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_places);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nav_view);
        // Setup drawer view

        locationRV = findViewById(R.id.idRVLocations);
        buildRecyclerView();

    }

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
        ArrayList<LocationModel> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (LocationModel item : locationModalArrayList) {
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
        locationModalArrayList = new ArrayList<>();

        locationModalArrayList.add(new LocationModel("Roma", "Good place for eating pizza", "https://www.fodors.com/wp-content/uploads/2018/10/HERO_UltimateRome_Hero_shutterstock789412159.jpg"));
        locationModalArrayList.add(new LocationModel("Paris", "City of love", "https://static.dw.com/image/60208186_303.jpg"));
        locationModalArrayList.add(new LocationModel("Brussels", "Belgium's treasure", "https://images.contentstack.io/v3/assets/blt00454ccee8f8fe6b/bltb5cb5d32753041a6/61bc44b0abe5293e65780e91/US_Brussels_Belgium_Header.jpg"));
        locationModalArrayList.add(new LocationModel("Berlin", "Nice place for beer", "https://theplanetd.com/images/places-to-visit-in-berlin-germany.jpg"));
        locationModalArrayList.add(new LocationModel("London", "Nice city", "https://www.history.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTYyNDg1MjE3MTI1Mjc5Mzk4/topic-london-gettyimages-760251843-promo.jpg"));
        locationModalArrayList.add(new LocationModel("Bucharest", "Beautiful but crowded", "https://freetourcommunity.com/wp-content/uploads/2020/09/PalaceofParliament-Bucharest.jpg"));
        locationModalArrayList.add(new LocationModel("Istanbul", "Did someone say kebab?", "https://idsb.tmgrup.com.tr/ly/uploads/images/2020/04/17/thumbs/800x531/31299.jpg"));
        locationModalArrayList.add(new LocationModel("Amsterdam", "So many channels", "https://cdn.britannica.com/30/180130-138-4FC01CDD/Overview-Amsterdam.jpg?w=800&h=450&c=crop"));
        locationModalArrayList.add(new LocationModel("Athens", "One of the most historical cities", "https://www.discovergreece.com/sites/default/files/styles/hd_half/public/2019-11/1-the-acropolis-hill-in-athens_istock_000044148298.jpg?itok=l7GF9JwA"));
        locationModalArrayList.add(new LocationModel("Barcelona", "Everything for everyone", "https://images.unsplash.com/photo-1587789202069-f57c846b85db?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmFyY2Vsb25hJTIwY2l0eXxlbnwwfHwwfHw%3D&w=1000&q=80"));

        // initializing our adapter class.
        adapter = new LocationAdapter(locationModalArrayList, HappyPlacesActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        locationRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        locationRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        locationRV.setAdapter(adapter);

    }
}