package com.example.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class FramelayoutActivity extends AppCompatActivity {
    ImageButton Led1, Led2, Led3, Led4, Led5, Led6, Led7, Led8;
    boolean check = false;
    Boolean arrayLed[] = new Boolean[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);

        for (int i = 0 ; i < 8 ; i++ ){
            arrayLed[i] = false;
        }

        Button btnOn = (Button) findViewById(R.id.btn_Turnon);
        Button btnReset = (Button) findViewById(R.id.btn_Reset);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = true;
                btnOn.setText("RANDOM");
                Random rdn = new Random();
                int rdnIndex = rdn.nextInt(8);

                if(rdnIndex == 0){
                    Led1 = (ImageButton) findViewById(R.id.btn_Led1);
                    Led1.setImageResource(R.drawable.open);
                    checkLed(Led1,0);
                } else if (rdnIndex == 1){
                    Led2 = (ImageButton) findViewById(R.id.btn_Led2);
                    Led2.setImageResource(R.drawable.open);
                    checkLed(Led2,1);
                }else if (rdnIndex == 2){
                    Led3 = (ImageButton) findViewById(R.id.btn_Led3);
                    Led3.setImageResource(R.drawable.open);
                    checkLed(Led3,2);
                }else if (rdnIndex == 3){
                    Led4 = (ImageButton) findViewById(R.id.btn_Led4);
                    Led4.setImageResource(R.drawable.open);
                    checkLed(Led4,3);
                }else if (rdnIndex == 4){
                    Led5 = (ImageButton) findViewById(R.id.btn_Led5);
                    Led5.setImageResource(R.drawable.open);
                    checkLed(Led5,4);
                }else if (rdnIndex == 5){
                    Led6 = (ImageButton) findViewById(R.id.btn_Led6);
                    Led6.setImageResource(R.drawable.open);
                    checkLed(Led6,5);
                }else if (rdnIndex == 6){
                    Led7 = (ImageButton) findViewById(R.id.btn_Led7);
                    Led7.setImageResource(R.drawable.open);
                    checkLed(Led7,6);
                }else{
                    Led8 = (ImageButton) findViewById(R.id.btn_Led8);
                    Led8.setImageResource(R.drawable.open);
                    checkLed(Led8,7);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                btnOn.setText("TURN ON");
                Led1 = (ImageButton) findViewById(R.id.btn_Led1);
                Led2 = (ImageButton) findViewById(R.id.btn_Led2);
                Led3 = (ImageButton) findViewById(R.id.btn_Led3);
                Led4 = (ImageButton) findViewById(R.id.btn_Led4);
                Led5 = (ImageButton) findViewById(R.id.btn_Led5);
                Led6 = (ImageButton) findViewById(R.id.btn_Led6);
                Led7 = (ImageButton) findViewById(R.id.btn_Led7);
                Led8 = (ImageButton) findViewById(R.id.btn_Led8);
                Led1.setImageResource(R.drawable.close);
                Led2.setImageResource(R.drawable.close);
                Led3.setImageResource(R.drawable.close);
                Led4.setImageResource(R.drawable.close);
                Led5.setImageResource(R.drawable.close);
                Led6.setImageResource(R.drawable.close);
                Led7.setImageResource(R.drawable.close);
                Led8.setImageResource(R.drawable.close);
            }
        });

    }

    public void checkLed(ImageButton led ,int index){
        if(check){
            arrayLed[index] = !arrayLed[index];
            if(arrayLed[index]){
                led.setImageResource(R.drawable.open);
            }
            else{
                led.setImageResource(R.drawable.close);
            }
        }
    }

    public void setLed1(View v){
        Led1 = (ImageButton) findViewById(R.id.btn_Led1);
        checkLed(Led1,0);

    }
    public void setLed2(View v){
        Led2 = (ImageButton) findViewById(R.id.btn_Led2);
        checkLed(Led2,1);

    }
    public void setLed3(View v){
        Led3 = (ImageButton) findViewById(R.id.btn_Led3);
        checkLed(Led3,2);

    }
    public void setLed4(View v){
        Led4 = (ImageButton) findViewById(R.id.btn_Led4);
        checkLed(Led4,3);

    }
    public void setLed5(View v){
        Led5 = (ImageButton) findViewById(R.id.btn_Led5);
        checkLed(Led5,4);

    }
    public void setLed6(View v){
        Led6 = (ImageButton) findViewById(R.id.btn_Led6);
        checkLed(Led6,5);

    }
    public void setLed7(View v){
        Led7 = (ImageButton) findViewById(R.id.btn_Led7);
        checkLed(Led7,6);

    }
    public void setLed8(View v){
        Led8 = (ImageButton) findViewById(R.id.btn_Led8);
        checkLed(Led8,7);

    }
}