package com.example.helloandroid18ct2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    //create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Call onCreate !!");
        Log.v(TAG,"onCreate - Verbon");

    }
    //start
    @Override
    protected void onStart (){
        super.onStart();
        System.out.println("Call onStart !!");
    }
    //resume
    @Override
    protected void onResume (){
        super.onResume();
        System.out.println("Call onResume !!");
    }
    //pause
    @Override
    protected void onPause (){
        super.onPause();
        System.out.println("Call onPause !!");
    }
    //stop
    @Override
    protected void onStop (){
        super.onStop();
        System.out.println("Call onStop !!");
    }
    //restart
    @Override
    protected void onRestart (){
        super.onRestart();
        System.out.println("Call onRestart !!");
    }
    //destroy
    @Override
    protected void onDestroy (){
        super.onDestroy();
        System.out.println("Call onDestroy !!");
    }

    public void doStart ( View v){
        Intent i1 = new Intent(this, HelloActivity.class);
        startActivity(i1);
    }

    public void doMusic ( View v){
        Intent i1 = new Intent(this, MusicActivity.class);
        startActivity(i1);
    }

    public void doQuo ( View v){
        Intent i1 = new Intent(this, QuotationActivity.class);
        startActivity(i1);
    }

    public void doLogin ( View v){
        Intent i1 = new Intent(this, BMIActivity.class);
        startActivity(i1);
    }
}