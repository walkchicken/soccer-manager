package com.example.helloandroid18ct2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class QuotationActivity extends AppCompatActivity {
    String quo[] ;
    int color_Quo[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        showQuo();
        Button btn_Quo = (Button) findViewById(R.id.btn_Quo);
        btn_Quo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuo();
            }
        });
    }

    private void showQuo(){
        Random rdn = new Random();
        Resources res = getResources();
        quo = res.getStringArray(R.array.quo);
        color_Quo = res.getIntArray(R.array.color_Quo);
        String title = quo[rdn.nextInt(quo.length)];
        int color = color_Quo[rdn.nextInt(color_Quo.length)];
        int size = 20 + rdn.nextInt(15);
        int gravities[] = {Gravity.CENTER,Gravity.LEFT,Gravity.RIGHT,Gravity.TOP, Gravity.BOTTOM};
        int gravity = gravities[rdn.nextInt(gravities.length)];

        TextView txt_Quotation = (TextView) findViewById(R.id.txt_Quo);
        txt_Quotation.setText(title);
        txt_Quotation.setTextColor(color);
        txt_Quotation.setTextSize(size);
        txt_Quotation.setGravity(gravity);
    }
}