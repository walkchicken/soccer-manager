package com.example.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.InputStream;

public class AssetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        RadioButton logo1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton logo2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton logo3 = (RadioButton) findViewById(R.id.radioButton3);
        ImageView imgv = (ImageView) findViewById(R.id.imageView2);
        switch(view.getId()) {
            case R.id.radioButton:
                try {
                    AssetManager assetManager = getAssets();
                    InputStream is = assetManager.open("open.png");
                    Drawable image = Drawable.createFromStream(is,null);
                    imgv.setImageDrawable(image);
                }
                catch(Exception e){
                    Toast.makeText(this,"error" + e,Toast.LENGTH_LONG).show();
                }
                logo2.setChecked(!checked);
                logo3.setChecked(!checked);
                break;
            case R.id.radioButton2:
                try {
                    AssetManager assetManager = getAssets();
                    InputStream is = assetManager.open("close.png");
                    Drawable image = Drawable.createFromStream(is,null);
                    imgv.setImageDrawable(image);
                }
                catch(Exception e){
                    Toast.makeText(this,"error" + e,Toast.LENGTH_LONG).show();
                }
                logo1.setChecked(!checked);
                logo3.setChecked(!checked);
                break;
            case R.id.radioButton3:
                try {
                    AssetManager assetManager = getAssets();
                    InputStream is = assetManager.open("close.png");
                    Drawable image = Drawable.createFromStream(is,null);
                    imgv.setImageDrawable(image);
                }
                catch(Exception e){
                    Toast.makeText(this,"error" + e,Toast.LENGTH_LONG).show();
                }
                logo1.setChecked(!checked);
                logo2.setChecked(!checked);
                break;
        }
    }
}