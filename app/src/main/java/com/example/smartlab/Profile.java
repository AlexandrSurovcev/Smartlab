package com.example.smartlab;

import static com.example.smartlab.R.layout.fragment_analys;
import static com.example.smartlab.R.layout.fragment_profile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Profile extends Fragment {
    SharedPreferences preferences;
    TextView btnCreate,editname,editpatronymic,editsurname,editdateofbirth;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_profile, container, false);
        //поля ввода
        editname = v.findViewById(R.id.editname);
        editpatronymic = v.findViewById(R.id.editpatronymic);
        editsurname = v.findViewById(R.id.editsurname);
        editdateofbirth = v.findViewById(R.id.editdateofbirth);
        preferences = getActivity().getSharedPreferences("UserInfo", 0);
        editname.setText(preferences.getString("name",null));
        editpatronymic.setText(preferences.getString("patronymic",null));
        editsurname.setText(preferences.getString("surname",null));
        return v;
    }
}