package com.example.smartlab;

import static com.example.smartlab.R.layout.fragment_analys;
import static com.example.smartlab.R.layout.fragment_profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Profile extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_profile, container, false);
        return v;
    }
}