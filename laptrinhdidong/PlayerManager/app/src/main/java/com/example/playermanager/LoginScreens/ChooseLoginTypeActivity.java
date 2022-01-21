package com.example.playermanager.LoginScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.playermanager.R;

public class ChooseLoginTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_type);
    }

    public void goToPlayerLogin(View view) {
        startActivity(new Intent(ChooseLoginTypeActivity.this,PlayerLoginActivity.class));
    }

    public void goToHallLogin(View view) {
        startActivity(new Intent(ChooseLoginTypeActivity.this,HallLoginActivity.class));
    }

    public void goToAdminLogin(View view) {
        startActivity(new Intent(ChooseLoginTypeActivity.this,AdminLoginActivity.class));
    }

}