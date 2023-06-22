package com.example.smartlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMenu extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Profile profileFragment = new Profile();
    Analys analysisFragment = new Analys();
    Result resultFragment  = new Result();
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_analys);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, analysisFragment).commit();

    }
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_analys:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, analysisFragment).commit();
                        return true;
                case R.id.navigation_result:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
                    return true;
                case R.id.navigation_support:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
                    return true;
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                    return true;
            }
            return false;
        }


    public void onNavigationItemMenu(int item){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(item ==1){
            fragmentTransaction.replace(R.id.navigation_profile, profileFragment);
        }
    }
    }