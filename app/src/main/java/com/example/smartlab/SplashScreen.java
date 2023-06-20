package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences preferences;
    boolean first = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences = getSharedPreferences("UserInfo", 0);
        SharedPreferences.Editor editor = preferences.edit();

        Thread logoTimer = new Thread()
        {
            public void run()
            {
                try
                {
                    int logoTimer = 0;
                    while(logoTimer < 2000)
                    {
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    if(!preferences.getBoolean("firstenter",false)){
                        first =true;
                        editor.putBoolean("firstenter", first);
                        editor.apply();
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        if(preferences.getBoolean("pincodeisVerify",false)){
                            Intent intent = new Intent(SplashScreen.this, PinCode.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(SplashScreen.this, SignLogin.class);
                            startActivity(intent);
                        }
                    }


                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}