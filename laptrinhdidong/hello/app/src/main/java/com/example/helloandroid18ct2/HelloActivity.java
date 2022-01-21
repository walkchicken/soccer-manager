package com.example.helloandroid18ct2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Button btn1 = (Button) findViewById(R.id.btn_exit);
        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        Button btn4 = (Button) findViewById(R.id.btn_change);
        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Onchange();
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn_img1);
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                changeImg1();
            }
        });

        Button btn3 = (Button) findViewById(R.id.btn_img2);
        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                changeImg2();
            }
        });
    }

    public void changeImg1 (){
        ImageView img1 = (ImageView) findViewById(R.id.imageView);
        img1.setImageResource(R.drawable.dhkt1);
    }

    public void changeImg2 (){
        ImageView img1 = (ImageView) findViewById(R.id.imageView);
        img1.setImageResource(R.drawable.dhkt2);
    }

    public void Onchange() {
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        TextView text_title = (TextView) findViewById(R.id.text_title);
        String title = text_title.getText().toString();

        TextView text_color = (TextView) findViewById(R.id.text_color);
        String color = text_color.getText().toString();

        TextView text_size = (TextView) findViewById(R.id.text_size);
        String size = text_size.getText().toString();

        int color_number = Integer.parseInt(color);
        int size_number = Integer.parseInt(size);
        if(color_number == 1) color_number = Color.RED ;
        else if(color_number == 2) color_number = Color.GREEN ;
        else if(color_number == 3) color_number = Color.YELLOW;
        else color_number = Color.BLUE;

        textView2.setText(title);
        textView2.setTextColor(color_number);
        textView2.setTextSize(size_number);
    }

}