package com.example.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LinearlayoutActivity extends AppCompatActivity {
    EditText txtNumberone, txtNumbertwo;
    TextView txtKetqua;
    Button btnCong, btnTru, btnNhan, btnChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearlayout);

        txtNumberone = (EditText) findViewById(R.id.txt_numberone);
        txtNumbertwo = (EditText) findViewById(R.id.txt_numbertwo);
        txtKetqua = (TextView) findViewById(R.id.txt_ketqua);

        btnCong = (Button) findViewById(R.id.btn_cong);
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int Number1 = Integer.parseInt(txtNumberone.getText().toString());
               int Number2 = Integer.parseInt(txtNumbertwo.getText().toString());
               int Tong = Number1 + Number2 ;
               txtKetqua.setText(Tong +"");
            }
        });

        btnTru = (Button) findViewById(R.id.btn_tru);
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Number1 = Integer.parseInt(txtNumberone.getText().toString());
                int Number2 = Integer.parseInt(txtNumbertwo.getText().toString());
                int Hieu = Number1 - Number2 ;
                txtKetqua.setText(Hieu +"");
            }
        });

        btnNhan = (Button) findViewById(R.id.btn_nhan);
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Number1 = Integer.parseInt(txtNumberone.getText().toString());
                int Number2 = Integer.parseInt(txtNumbertwo.getText().toString());
                int Tich = Number1 * Number2 ;
                txtKetqua.setText(Tich +"");
            }
        });

        btnChia = (Button) findViewById(R.id.btn_chia);
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Number1 = Integer.parseInt(txtNumberone.getText().toString());
                int Number2 = Integer.parseInt(txtNumbertwo.getText().toString());
                int Thuong = Number1 / Number2 ;
                txtKetqua.setText(Thuong +"");
            }
        });
    }
}