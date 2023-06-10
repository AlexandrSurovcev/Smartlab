package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }
    public void Back(View view){
        Intent intent = new Intent(Order.this, ActivityMenu.class);
        startActivity(intent);
    }
    public void showDialog(View view){
        onCreateDialog dialog = new onCreateDialog();
        dialog.show(getSupportFragmentManager(),"custom");
    }
}