package com.example.smartlab;

import static com.example.smartlab.R.layout.fragment_analys;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Analys extends Fragment {
    TextView popular,covid, comprehensive;
    EnableTextView enableText = new EnableTextView();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_analys, container, false);
        popular = v.findViewById(R.id.popular);
        covid = v.findViewById(R.id.covid);
        comprehensive = v.findViewById(R.id.comprehensive);
        enableText.onEnable(popular,getContext());


        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableText.setAble(popular, covid, comprehensive, getContext());
            }
        });
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableText.setAble(covid, popular, comprehensive, getContext());
            }
        });
        comprehensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableText.setAble(comprehensive, popular, covid, getContext());
            }
        });
        return v;
    }
}