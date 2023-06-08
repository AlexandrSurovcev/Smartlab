package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignLogin extends AppCompatActivity {
Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_login);
        btnNext = findViewById(R.id.btnNext);
    }
    public void onClick1(View view){
        Intent intent = new Intent(SignLogin.this, codemail.class);
        startActivity(intent);
    }
    public void Next(View view){
            Intent intent = new Intent(SignLogin.this,PinCode.class);
            startActivity(intent);
    }
}