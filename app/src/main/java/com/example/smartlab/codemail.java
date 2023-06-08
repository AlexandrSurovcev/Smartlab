package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class codemail extends AppCompatActivity {
TextView mTimer, pin1,pin2,pin3,pin4;
String code;
ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codemail);
        btnBack = findViewById(R.id.btnBack);
        mTimer = findViewById(R.id.mTimer);
        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        pin3 = findViewById(R.id.pin3);
        pin4 = findViewById(R.id.pin4);
        new CountDownTimer(60000,1000){
            public void onTick(long millisUntilFinished){
                mTimer.setText("отправить код повторно можно будет через "+ millisUntilFinished/1000+" секунд");
            }
            public void onFinish(){
                mTimer.setText("Отправить код повторно");
            }
        }.start();

        pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    code += charSequence.toString();
                    pin2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    code += charSequence.toString();
                    pin3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==1){
                    code += charSequence.toString();
                    pin4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                code += charSequence.toString();
                Toast.makeText(getApplicationContext(),code,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(codemail.this, PinCode.class);
                startActivity(intent);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


    }
    public void Back(View view){
        Intent intent = new Intent(codemail.this, SignLogin.class);
        startActivity(intent);
    }
}