package com.example.loginregister.activity.loginregister.Clubs;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Clubs> clubsList;
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra("id", clubsList.get(position).getId());
                intent.putExtra("name", clubsList.get(position).getName());
                intent.putExtra("weight", clubsList.get(position).getWeight());
                intent.putExtra("height", clubsList.get(position).getHeight());
                intent.putExtra("position", clubsList.get(position).getPosition());
                intent.putExtra("picture", clubsList.get(position).getPicture());
                intent.putExtra("birth", clubsList.get(position).getBirth());
                startActivity(intent);

            }

            @Override
            public void onLoveClick(View view, int position) {

                final int id = clubsList.get(position).getId();
                final Boolean love = clubsList.get(position).getLove();
                final ImageView mLove = view.findViewById(R.id.love);

                if (love){
                    mLove.setImageResource(R.drawable.likeof);
                    clubsList.get(position).setLove(false);
                    updateLove("update_love", id, false);
                    adapter.notifyDataSetChanged();
                } else {
                    mLove.setImageResource(R.drawable.likeon);
                    clubsList.get(position).setLove(true);
                    updateLove("update_love", id, true);
                    adapter.notifyDataSetChanged();
                }

            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Pet...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getClubs(){

        Call<List<Clubs>> call = apiInterface.getClubs();
        call.enqueue(new Callback<List<Clubs>>() {
            @Override
            public void onResponse(Call<List<Clubs>> call, Response<List<Clubs>> response) {
                progressBar.setVisibility(View.GONE);
                clubsList = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(clubsList, MainActivity.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Clubs>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateLove(final String key, final int id, final Boolean love){

        Call<Clubs> call = apiInterface.updateLove(key, id, love);

        call.enqueue(new Callback<Clubs>() {
            @Override
            public void onResponse(Call<Clubs> call, Response<Clubs> response) {

                Log.i(MainActivity.class.getSimpleName(), "Response "+response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Clubs> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getClubs();
    }

}
