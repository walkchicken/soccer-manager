package com.example.playermanager.MainScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playermanager.LoginScreens.ChooseLoginTypeActivity;
import com.example.playermanager.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void goToLogin(View view) {
        startActivity(new Intent(SplashActivity.this, ChooseLoginTypeActivity.class));
    }
}