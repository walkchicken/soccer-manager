package com.example.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GridlayoutActivity extends AppCompatActivity {
    Button btn_New, btn1, btn2, btn3,btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15;
    TextView txtScore;
    int score = 0;
    int arr_Number[] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayout);

        txtScore = (TextView) findViewById(R.id.txt_Score);
        txtScore.setText(score+"");

        btn1 = (Button) findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn1.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn2 = (Button) findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn2.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn3 = (Button) findViewById(R.id.btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn3.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn4 = (Button) findViewById(R.id.btn_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn4.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn5 = (Button) findViewById(R.id.btn_5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn5.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn6 = (Button) findViewById(R.id.btn_6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn6.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn7 = (Button) findViewById(R.id.btn_7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn7.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn8 = (Button) findViewById(R.id.btn_8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn8.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn9 = (Button) findViewById(R.id.btn_9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn9.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn10 = (Button) findViewById(R.id.btn_10);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn10.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn11 = (Button) findViewById(R.id.btn_11);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn11.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn12 = (Button) findViewById(R.id.btn_12);
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn12.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn13 = (Button) findViewById(R.id.btn_13);
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn13.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn14 = (Button) findViewById(R.id.btn_14);
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn14.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn15 = (Button) findViewById(R.id.btn_15);
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btnNumber = Integer.parseInt(btn15.getText().toString());
                checkNumber(rdnNumber(arr_Number),btnNumber);
            }
        });

        btn_New = (Button) findViewById(R.id.btn_New);
        btn_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                txtScore.setText("Score: "+score+"\n"+ "Result: "+ score);
            }
        });
    }

    private void checkNumber(int rdnindex, int btnnumber) {
        if(rdnindex == btnnumber){
            score += 10;
            txtScore.setText("Score: "+score+"\n"+ "Result: "+ rdnindex);
        }else {
            txtScore.setText("Score: "+score+"\n"+ "Result: "+ rdnindex);
        }
    }

    private int rdnNumber(int[] arr_Number){
        Random rdn = new Random();
        Resources res = getResources();
        arr_Number = res.getIntArray(R.array.arr_Number);
        int rdnIndex = arr_Number[rdn.nextInt(arr_Number.length)];
        return rdnIndex;
    }
}