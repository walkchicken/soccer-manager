package com.example.helloandroid18ct2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class BMIActivity extends AppCompatActivity {
    int color_Quo[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        TextView kg = (TextView) findViewById(R.id.editTextNumber);
        TextView cm = (TextView) findViewById(R.id.editTextNumber2);
        Button exit = (Button) findViewById(R.id.button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Switch swt = (Switch) findViewById(R.id.swt);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rndBG();
            }
        });

        SeekBar sk = (SeekBar) findViewById(R.id.seekBar);

        sk.setMax(150);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                kg.setText(String.valueOf(progress));
                cm.setText(String.valueOf(progress));
            }
        });
    }

    public void rndBG() {
        Random rdn = new Random();
        Resources res = getResources();
        color_Quo = res.getIntArray(R.array.color_Quo);
        int color = color_Quo[rdn.nextInt(color_Quo.length)];
        RelativeLayout Layout = (RelativeLayout) findViewById(R.id.bmi);
        Layout.setBackgroundColor(color);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        RadioButton logo1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton logo2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton logo3 = (RadioButton) findViewById(R.id.radioButton3);
        ImageView imgv = (ImageView) findViewById(R.id.imageView3);
        switch(view.getId()) {
            case R.id.radioButton:
                imgv.setImageResource(R.drawable.dhkt1);
                logo2.setChecked(!checked);
                logo3.setChecked(!checked);
                break;
            case R.id.radioButton2:
                imgv.setImageResource(R.drawable.dhkt2);
                logo1.setChecked(!checked);
                logo3.setChecked(!checked);
                break;
            case R.id.radioButton3:
                imgv.setImageResource(R.drawable.logo);
                logo1.setChecked(!checked);
                logo2.setChecked(!checked);
                break;
        }
    }

    public void onResultClicked(View view) {
        TextView kg = (TextView) findViewById(R.id.editTextNumber);
        TextView cm = (TextView) findViewById(R.id.editTextNumber2);
        TextView result = (TextView) findViewById(R.id.textView10);

        int Number1 = Integer.parseInt(kg.getText().toString());
        int Number2 = (int) (Integer.parseInt(cm.getText().toString()) * 0.01);

        int BMI = Number1 / Number2*Number2 ;


        if(BMI < 18) {
            result.setText("Người gầy");

        }
            if(BMI >= 18 && BMI < 24.9 ){
                result.setText("Người bình thường");
            }else {
                if (BMI >= 25 && BMI < 29.9) {
                    result.setText("Người béo phì độ I ");
                } else {

                    if (BMI >= 30 && BMI < 34.9) {
                        result.setText("Người béo phì độ II ");
                    } else {
                        if (BMI > 35) {
                            result.setText("Người béo phì độ III ");
                        } else {
                            result.setText("ko xac dinh");
                        }
                    }
                }
            }


    }
}