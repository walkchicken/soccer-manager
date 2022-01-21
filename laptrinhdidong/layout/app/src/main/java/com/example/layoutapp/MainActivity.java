package com.example.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doFrame ( View v){
        Intent i1 = new Intent(this, FramelayoutActivity.class);
        startActivity(i1);
    }

    public void doLinear ( View v){
        Intent i1 = new Intent(this, LinearlayoutActivity.class);
        startActivity(i1);
    }

    public void doRelative ( View v){
        Intent i1 = new Intent(this, RelativelayoutActivity.class);
        startActivity(i1);
    }

    public void doGrid ( View v){
        Intent i1 = new Intent(this, AssetsActivity.class);
        startActivity(i1);
    }

}