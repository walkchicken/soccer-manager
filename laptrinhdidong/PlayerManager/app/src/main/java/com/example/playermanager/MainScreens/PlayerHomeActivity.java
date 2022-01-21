package com.example.playermanager.MainScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.playermanager.Adapter.HallsListAdapter;
import com.example.playermanager.Models.MD_Hall;
import com.example.playermanager.R;
import com.example.playermanager.SQLite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PlayerHomeActivity extends AppCompatActivity implements HallsListAdapter.ClickListener {

    RecyclerView hallsRecyclerView;
    HallsListAdapter hallsListAdapter;
    List<MD_Hall> hallsList = new ArrayList<>();
    SharedPreferences sharedpreferences;
    public static final String myPreference = "mypref";
    public static final String halldataloaded = "hallDataLoaded";
    public MD_Hall model = new MD_Hall();
    DatabaseHelper dh;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player_home);
        setTitle("Halls");
        user = getIntent().getStringExtra("user");
        dh  = new DatabaseHelper(this);
        sharedpreferences = getSharedPreferences(String.valueOf(myPreference), Context.MODE_PRIVATE);
        if((sharedpreferences.getString(halldataloaded, "").equals("") || sharedpreferences.getString(halldataloaded, "").equals(null))
                && user.equalsIgnoreCase("Player")){
            loadHallsData();
        } else if((sharedpreferences.getString(halldataloaded, "").equals("") || sharedpreferences.getString(halldataloaded, "").equals(null))
                && user.equalsIgnoreCase("Hall")) {
            loadHallsData();
        } else {
            hallsList = dh.retrieveHallData();
        }
        init();
    }

    //This method sets data of hall in model which will be used by HallsListAdapter in order to show in the list format
    public void loadHallsData(){
        String hallNames[]={"Pro Football Hall of Fame","Tom Beck","Matty Bell","Mike Bellotti","Hugo Bezdek","Roy Kidd",
                "Ralph Jordan", "Frank Kush","Andrew Kerr","Bill Ingram"};

        String hallAddresses[]={"490 E South, Orlando, FL", "840 West, Washington DC", "London Place, London", "310 Wall Street, New York",
                "510, Down Town, New Jersey", "366 Idaho Falls, Ohio", "111 Lacinia Avenue, Ohio", "Sunset Valley, Texas",
                "4801 Republic of Texas Blvd, Austin", "Washington Square Park, NY"};

        int hallTotalCapacity[]={12,10,8,6,4,2,3,6,7,5};

        int hallReviews[]={5,10,2,4,5,6,7,8,9,1};

        float ratings[]={2.6f,3f,4f,4.6f,4f,2.6f,3f,4f,4.6f,4f};
        int hallPos[] = {0,1,2,3,4,5,6,7,8,9};
        for(int i=0; i<hallNames.length; i++){
            //insert static hall data into MD_Hall model
            model.setName(hallNames[i]);
            model.setAddress(hallAddresses[i]);
            model.setTotalCapacity(hallTotalCapacity[i]);
            model.setReviewCount(hallReviews[i]);
            model.setRating(ratings[i]);
            model.setHallPosition(hallPos[i]);
            //insert static hall data into hallList list
            hallsList.add(model);
            //insert hall data in table
            boolean isInserted = dh.insertHallData(model.getName(),model.getAddress(),model.getTotalCapacity(),model.getReviewCount(),model.getRating(),model.getHallPosition());
            if(isInserted){
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(halldataloaded, "DataLoaded");
                editor.commit();
            }
        }
    }

    public void loadDataInHallTable(){
        for(int i=0; i<hallsList.size(); i++){

        }
    }

    //setting up LinearLayout Manager and HallsListAdapter in recycler view
    public void init(){
        hallsRecyclerView = findViewById(R.id.hallsRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PlayerHomeActivity.this, LinearLayoutManager.VERTICAL, false);
        hallsRecyclerView.setLayoutManager(linearLayoutManager);

        hallsListAdapter = new HallsListAdapter(this, hallsList, PlayerHomeActivity.this);
        hallsRecyclerView.setAdapter(hallsListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_home_menu, menu);

        return true;
    }

    public void searchHallsByLocation(MenuItem item) {

    }


    //on click of recylcer view item which opens new Activity
    @Override
    public void onClick(MD_Hall model, int position) {
        Intent intent = new Intent(PlayerHomeActivity.this , HallDetailActivity.class);
        intent.putExtra("HallDataModel", model);
        intent.putExtra("position", position);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
