package com.example.helloandroid18ct2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class StudentActivity extends AppCompatActivity {
    int color_Quo[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Switch swt = (Switch) findViewById(R.id.switch1);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rnd();
            }
        });
        ToggleButton tgbtn = (ToggleButton) findViewById(R.id.toggleButton);
        tgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rnd();
            }
        });
        init();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        RadioButton male = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton female = (RadioButton) findViewById(R.id.radioButton7);
        switch(view.getId()) {
            case R.id.radioButton6:
                rnd();
                female.setChecked(!checked);
                    break;
            case R.id.radioButton7:
                rnd();
                male.setChecked(!checked);
                    break;
        }
    }

    public void onbgcheck(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkBox:
                rnd();
                break;
            case R.id.checkBox2:
                rnd();
                break;
            case R.id.checkBox3:
                rnd();
                break;
            case R.id.switch1:
                rnd();
                break;
            case R.id.toggleButton:
                rnd();
                break;
        }
    }

    public void rnd() {
        Random rdn = new Random();
        Resources res = getResources();
        color_Quo = res.getIntArray(R.array.color_Quo);
        int color = color_Quo[rdn.nextInt(color_Quo.length)];
        RelativeLayout Layout = (RelativeLayout) findViewById(R.id.ss);
        Layout.setBackgroundColor(color);
    }

    private String showCheckbox()  {
        CheckBox checkBox1 = findViewById(R.id.checkBox);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);

        String message = null;
        if(checkBox1.isChecked()) {
            message =  checkBox1.getText().toString();
        }
        if(checkBox2.isChecked()) {
            if(message== null)  {
                message =  checkBox2.getText().toString();
            } else {
                message += ", " + checkBox2.getText().toString();
            }
        }
        if(checkBox3.isChecked()) {
            if(message== null)  {
                message =  checkBox3.getText().toString();
            } else {
                message += ", " + checkBox3.getText().toString();
            }
        }
        message = "Hobbies: " + message;
        return message ;
    }

    private String showRadio() {
        RadioButton male = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton female = (RadioButton) findViewById(R.id.radioButton7);
        String message = null;
        if(male.isChecked()) {
            message =  male.getText().toString();
        }
        if(female.isChecked()){
            message =  female.getText().toString();
        }

        return "Gender: "+ message;
    }


    private void init() {
        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView result = (TextView) findViewById(R.id.result);
        Button cancle = (Button) findViewById(R.id.cancle);
        Button infor = (Button) findViewById(R.id.infor);
        Switch swt = (Switch) findViewById(R.id.switch1);
        ToggleButton tgbtn = (ToggleButton) findViewById(R.id.toggleButton);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgname = name.getText().toString();
                String msgemail = email.getText().toString();
                String msgcb = showCheckbox();
                String msgrdo = showRadio();
                String msgswt = null;
                if (swt.isChecked()) {
                    rnd();
                    msgswt = swt.getTextOn().toString();
                } else {
                    rnd();
                    msgswt = swt.getTextOff().toString();
                }
                String msgtgb = null;
                if (tgbtn.isChecked()) {
                    rnd();
                    msgtgb = tgbtn.getTextOn().toString();
                } else {
                    rnd();
                    msgtgb = tgbtn.getTextOff().toString();
                }
                String msg = msgname + "\n" + msgemail + "\n" + msgcb + "\n"
                        + msgrdo + "\n" + msgswt + "\n" + msgtgb;
                result.setText(msg);

            }
        });
    }
}