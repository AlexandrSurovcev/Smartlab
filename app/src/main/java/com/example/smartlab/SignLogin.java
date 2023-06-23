package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignLogin extends AppCompatActivity {
TextView btnNext,mail;
EnableTextView ETV = new EnableTextView();
boolean email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_login);
        btnNext = findViewById(R.id.btnNext);
        mail = findViewById(R.id.mail);
        //ПРОВЕРКА ПОЧТЫ
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
                if(mail.getText().toString().matches(emailPattern)){
                    email=true;
                    ETV.onEnableBtn(btnNext,getApplicationContext());
                }else {
                    email=false;
                    ETV.onDisableBtn(btnNext,getApplicationContext());
                }
            }
        });
    }
    //ПОЛУЧИТЬ КОД
    public void onClick1(View view){
        if(email){
            Intent intent = new Intent(SignLogin.this, codemail.class);
            startActivity(intent);
        }else Toast.makeText(SignLogin.this, "Неверно введена почта", Toast.LENGTH_SHORT).show();
    }
    //С ПОМОЩЬЮ ЯНДЕКСА
    public void Next(View view){
            Intent intent = new Intent(SignLogin.this,PinCode.class);
            startActivity(intent);
    }
}