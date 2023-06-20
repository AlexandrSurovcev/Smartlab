package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PinCode extends AppCompatActivity {
TextView btnNum1, btnNum2, btnNum3,btnNum4,btnNum5,btnNum6,btnNum7,btnNum8,btnNum9,btnNum0, infocreatepin,createpin;
ImageView num1,num2,num3,num4;
    SharedPreferences preferences;
String pincode = "";
    Boolean pincodeisVerify1;
imageNumber imagenumber = new imageNumber();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);
        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);
        btnNum5 = findViewById(R.id.btnNum5);
        btnNum6 = findViewById(R.id.btnNum6);
        btnNum7 = findViewById(R.id.btnNum7);
        btnNum8 = findViewById(R.id.btnNum8);
        btnNum9 = findViewById(R.id.btnNum9);
        btnNum0 = findViewById(R.id.btnNum0);
        createpin = findViewById(R.id.createdpin);
        infocreatepin = findViewById(R.id.infocreatepin);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        preferences = getSharedPreferences("UserInfo", 0);
        if(preferences.getBoolean("pincodeisVerify",false)){
            createpin.setText("Введите пароль");
            infocreatepin.setText("Для проверки идентификации");
        }
    }

    public void onClick(View view){
        int x = 0;
        String s;
        switch (view.getId()){
            case R.id.btnNum1: x=1;break;
            case R.id.btnNum2: x=2;break;
            case R.id.btnNum3: x=3;break;
            case R.id.btnNum4: x=4;break;
            case R.id.btnNum5: x=5;break;
            case R.id.btnNum6: x=6;break;
            case R.id.btnNum7: x=7;break;
            case R.id.btnNum8: x=8;break;
            case R.id.btnNum9: x=9;break;
            case R.id.btnNum0: x=0;break;
        }
        if(x!=0){
            pincode += String.valueOf(x);
            imagenumber.getImg(num1,num2,num3,num4);
            if(pincode.length()==4){
                pincodeisVerify1 =  preferences.getBoolean("pincodeisVerify",false);
                if(pincodeisVerify1){
                    String pincodesaved =  preferences.getString("pincode",null);
                    if(pincode.equals(pincodesaved)){
                        if(preferences.getBoolean("createdcard",false)){
                            Intent intent = new Intent(PinCode.this, ActivityMenu.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(PinCode.this, CreateCardPatient.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        imagenumber.setDisable(num1,num2,num3,num4);
                        pincode = "";
                        Toast.makeText(getApplicationContext(),"Неверный пароль, попробуйте еще раз",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    SharedPreferences.Editor editor = preferences.edit();
                    pincodeisVerify1 = true;
                    editor.putBoolean("pincodeisVerify",pincodeisVerify1);
                    editor.putString("pincode",pincode);
                    editor.apply();
                    Intent intent = new Intent(PinCode.this, CreateCardPatient.class);
                    startActivity(intent);
                }
            }
        }
    }
    public void onClick1(View view){
        String s = pincode;
        String str = new StringBuilder(s).deleteCharAt(s.length()-1).toString();
        pincode = str;
        imagenumber.setImg(num1,num2,num3,num4);

    }
    public void Next(View view){
        Intent intent = new Intent(PinCode.this, CreateCardPatient.class);
        startActivity(intent);
    }
}