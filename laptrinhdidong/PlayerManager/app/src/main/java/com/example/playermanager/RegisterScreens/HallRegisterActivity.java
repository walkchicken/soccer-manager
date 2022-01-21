package com.example.playermanager.RegisterScreens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playermanager.R;
import com.example.playermanager.SQLite.DatabaseHelper;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class HallRegisterActivity extends AppCompatActivity {

    private final static int PLACE_PICKER_REQUEST = 999;
    boolean locPermGranted;
    EditText hallName,hallEmail,hallAbout,password,confirmpass;
    TextView hallAddress;
    Button registerHall;
    DatabaseHelper dh;
    String hallNameStr,hallEmailStr,hallAboutStr,hallPasswordStr,hallConfirmpassStr,hallAddressStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_register);
        dh = new DatabaseHelper(this);
        initviews();
        init();
    }

    public void init(){

        locPermGranted = false;
        requestLocation();
        registerHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hallNameStr = hallName.getText().toString();
                hallEmailStr = hallEmail.getText().toString();
                hallAboutStr = hallAbout.getText().toString();
                hallAddressStr = hallAddress.getText().toString();
                hallPasswordStr = password.getText().toString();
                hallConfirmpassStr = confirmpass.getText().toString();
                if (( hallNameStr == null || hallNameStr.equals("")) ||
                        (hallEmailStr == null || hallEmailStr.equals("")) ||
                        (hallAboutStr == null || hallAboutStr.equals("")) ||
                        (hallPasswordStr == null || hallPasswordStr.equals(""))||
                        (hallConfirmpassStr == null || hallConfirmpassStr.equals("")))
                {
                    Toast.makeText(HallRegisterActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
                    //Snackbar snackbar = Snackbar.make(view, "Please fill all details", Snackbar.LENGTH_LONG);
                    //snackbar.show();
                }
                else
                {
                    if (!hallPasswordStr.equals(hallConfirmpassStr)) {
                        Toast.makeText(HallRegisterActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                    }

                    else {

                        boolean isinserted = dh.insertHallRegisterData(hallNameStr, hallEmailStr, hallAddressStr, hallAboutStr, hallPasswordStr);

                        if (isinserted == true) {
                            Toast.makeText(getApplicationContext(), "Hall Registered successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Hall Register Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }


    public void initviews()
    {
        hallName= (EditText) findViewById(R.id.hallName);
        hallEmail= (EditText)findViewById(R.id.hallEmail);
        hallAddress = (TextView) findViewById(R.id.hallAddress);
        hallAbout = (EditText)findViewById(R.id.hallAbout);
        password = (EditText)findViewById(R.id.hallpass);
        confirmpass= (EditText)findViewById(R.id.hallconfirmpass);
        registerHall = (Button)findViewById(R.id.buttonRegisterHall);
    }

    public void requestLocation(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        locPermGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        locPermGranted = false;
                        if (response.isPermanentlyDenied()) {
                            // Ask for permission again
                            ActivityCompat.requestPermissions(HallRegisterActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    1);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public boolean checkIfLocationIsEnabled(){
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("In order to get location please enable your GPS");
            dialog.setPositiveButton("Open settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    HallRegisterActivity.this.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
            return false;
        }else{
            return true;
        }
    }

    public void goToPlayerLogin(View view) {
        onBackPressed();
    }

    public void registerHall(View view) {
    }

    public void goToHallLogin(View view) {
        onBackPressed();
    }

    public void getLocation(View view) {
        if(locPermGranted && checkIfLocationIsEnabled()){
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }else{
            requestLocation();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case PLACE_PICKER_REQUEST:
                    Place place = PlacePicker.getPlace(this, data);
                    String placeName = String.format("Place: %s", place.getName());
                    double latitude = place.getLatLng().latitude;
                    double longitude = place.getLatLng().longitude;

            }
        }
    }
}
