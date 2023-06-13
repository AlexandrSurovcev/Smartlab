package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Order extends AppCompatActivity {
    TextView dateshow;
BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        dateshow = findViewById(R.id.dateshow);
        dialog = new BottomSheetDialog(this);
        //inflate view
        createDialog();
        dateshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }
    private void createDialog(){
        View view  = getLayoutInflater().inflate(R.layout.dateandtime_layout,null,true);
        TextView ten = view.findViewById(R.id.ten);
        ImageView btnClose = view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        dialog.setContentView(view);
    }
    public void Back(View view){
        Intent intent = new Intent(Order.this, ActivityMenu.class);
        startActivity(intent);
    }
}