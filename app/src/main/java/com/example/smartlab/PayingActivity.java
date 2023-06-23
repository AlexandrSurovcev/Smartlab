package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PayingActivity extends AppCompatActivity {
TextView infopaying,onMain,check;
ImageView imageView2,imageView3;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);
        infopaying = findViewById(R.id.infopaying);
        progressBar = findViewById(R.id.progressBar);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        onMain = findViewById(R.id.onMain);
        onMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ActivityMenu.class);
                startActivity(intent);
            }
        });
        check = findViewById(R.id.check);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {infopaying.setText("Производим оплату...");
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {infopaying.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                imageView2.setVisibility(View.VISIBLE);imageView3.setVisibility(View.VISIBLE);
                onMain.setVisibility(View.VISIBLE);
                check.setVisibility(View.VISIBLE);

            }
        },4000);
//        Thread timer = new Thread()
//        {
//            public void run()
//            {
//                try
//                {
//                    int timer = 0;
//                    while(timer < 2000)
//                    {
//                        sleep(100);
//                        timer = timer +100;
//                    };
//                    infopaying.setText("dsfdsf");
//
//
//                }
//                catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//                finally
//                {
//                    finish();
//                }
//            }
//        };
//        timer.start();

    }
}