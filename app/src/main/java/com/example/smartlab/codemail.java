package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class codemail extends AppCompatActivity {
TextView mTimer;
ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codemail);
        btnBack = findViewById(R.id.btnBack);
        mTimer = findViewById(R.id.mTimer);
        new CountDownTimer(60000,1000){
            public void onTick(long millisUntilFinished){
                mTimer.setText("отправить код повторно можно будет через "+ millisUntilFinished/1000+" секунд");
            }
            public void onFinish(){
                mTimer.setText("Отправить код повторно");
            }
        }.start();
    }
    public void Back(View view){
        Intent intent = new Intent(codemail.this, SignLogin.class);
        startActivity(intent);
    }
}