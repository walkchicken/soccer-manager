package com.example.playermanager.RegisterScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.playermanager.R;
import com.example.playermanager.SQLite.DatabaseHelper;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class PlayerRegisterActivity extends AppCompatActivity {

    MaterialSpinner positionSpinner;
    EditText playername,playeremail,playerage,password,confirmpass;
    Button register;
    String playerNameStr,playerEmailStr,playerPasswordStr,confirmPassStr="";
    String playerAgeStr;
    DatabaseHelper dh;
    String playerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_register);
        initViews();
        dh  = new DatabaseHelper(this);
        init();
        populate();
    }

    public void initViews(){
        playername = (EditText)findViewById(R.id.playername);
        playeremail = (EditText)findViewById(R.id.playeremail);
        playerage = (EditText)findViewById(R.id.playerage);
        password = (EditText)findViewById(R.id.password);
        confirmpass = (EditText)findViewById(R.id.confirmpass);
        positionSpinner = findViewById(R.id.spinner);
        register = (Button)findViewById(R.id.buttonRegisterPlayer);
    }



    public void populate(){
        positionSpinner.setItems(getResources().getStringArray(R.array.position_array));
    }

    public void init()
    {
        positionSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                playerPosition = item.toString();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerNameStr = playername.getText().toString();
                playerAgeStr = playerage.getText().toString();
                playerEmailStr = playeremail.getText().toString();
                playerPasswordStr = password.getText().toString();
                confirmPassStr = confirmpass.getText().toString();

                if(( playerNameStr == null || playerNameStr.equals("")) ||
                        (playerAgeStr == null || playerAgeStr.equals("")) ||
                        (playerEmailStr == null || playerEmailStr.equals("")) ||
                        (playerPasswordStr == null || playerPasswordStr.equals("")) ||
                        (confirmPassStr == null || confirmPassStr.equals("")))
                {
                    Toast.makeText(PlayerRegisterActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
                }
                else {
                    if (!playerPasswordStr.equals(confirmPassStr)) {
                        Toast.makeText(PlayerRegisterActivity.this, "Passwords dont match", Toast.LENGTH_LONG).show();
                    } else {
                        boolean isInserted = dh.insertPlayerRegistration(playerNameStr, playerEmailStr, Integer.parseInt(playerAgeStr), playerPasswordStr, playerPosition);

                        if (isInserted) {
                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Register Unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void goToPlayerLogin(View view) {
        onBackPressed();
    }
}
