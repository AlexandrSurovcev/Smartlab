package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateCardPatient extends AppCompatActivity {
TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card_patient);
        skip = findViewById(R.id.skip);
    }

    public void Skip(View view){
        Intent intent = new Intent(CreateCardPatient.this,ActivityMenu.class);
        startActivity(intent);
    }
}