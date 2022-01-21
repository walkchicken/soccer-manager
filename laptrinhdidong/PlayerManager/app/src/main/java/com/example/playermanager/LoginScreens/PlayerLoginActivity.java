package com.example.playermanager.LoginScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.playermanager.MainScreens.PlayerHomeActivity;
import com.example.playermanager.R;
import com.example.playermanager.RegisterScreens.PlayerRegisterActivity;
import com.example.playermanager.SQLite.DatabaseHelper;

public class PlayerLoginActivity extends AppCompatActivity {
    EditText playerpassword,playeremail;
    Button login;
    String playerPasswordStr,playerEmailStr="";

    DatabaseHelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_player_login);
        dh = new DatabaseHelper(this);
        initviews();
        init();

    }

    public void initviews()
    {
        playerpassword = (EditText)findViewById(R.id.password);
        playeremail = (EditText)findViewById(R.id.email);
        login = (Button)findViewById(R.id.buttonLoginPlayer);
    }

    public void init()
    {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerPasswordStr=playerpassword.getText().toString();
                playerEmailStr=playeremail.getText().toString();

                Cursor res =  dh.checkLoginDetailsForPlayers(playerEmailStr,playerPasswordStr);
                if (res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Wrong EmailId or Password ", Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    Intent intent = new Intent(PlayerLoginActivity.this , PlayerHomeActivity.class);
                    intent.putExtra("user", "Player");
                    startActivity(intent);
                }
            }
        });
    }

    public void goToPlayerRegister(View view) {
        startActivity(new Intent(PlayerLoginActivity.this, PlayerRegisterActivity.class));
    }


}
