package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class Order extends AppCompatActivity {
    TextView dateshow,patientsList;
    AutoCompleteTextView patientsChoice;
    SharedPreferences preferences;
    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        preferences = getSharedPreferences("UserInfo", 0);
        dateshow = findViewById(R.id.dateshow);
        dateshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
        String patient1 = preferences.getString("surname",null)+" "+preferences.getString("name",null);
        String[] patientslist = {patient1};
        patientsChoice = findViewById(R.id.patientsChoice);
        patientsList = findViewById(R.id.patientsList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_items, patientslist);
        patientsChoice.setAdapter(arrayAdapter);
        patientsChoice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                patientsList.setText(patientsChoice.getText().toString());
                String pol = preferences.getString("gender",null);
                if(pol.equals("Мужской")){
                    patientsList.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.male),null,null,null);
                }else  patientsList.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.female),null,null,null);
            }
        });

    }

    //время
    String time = "";
    //выбор времени
    private void showBottomDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dateandtime_layout);


        String month = calendar.getDisplayName(Calendar.MONTH,Calendar.LONG_FORMAT,new Locale("ru"));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String[] dates = {"Сегодня , " + day + " " + month,"Завтра, " + (day+1)+ " " + month};
        AutoCompleteTextView dayChoice = dialog.findViewById(R.id.dayChoice);
        TextView daysList = dialog.findViewById(R.id.daysList);
        daysList.setText("Сегодня, " + day + " " + month);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_items, dates);
        dayChoice.setAdapter(arrayAdapter);
        dayChoice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                daysList.setText(dayChoice.getText().toString());
            }
        });
        
        ImageView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView btnConfirm = dialog.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateshow.setText(dayChoice.getText() + " " + time);
                dateshow.setTextColor(ContextCompat.getColor(Order.this, R.color.black));
                dialog.dismiss();
            }
        });

        TextView ten = dialog.findViewById(R.id.ten);
        enableTextView(ten,this);
        TextView one = dialog.findViewById(R.id.one);
        TextView two = dialog.findViewById(R.id.two);
        TextView three = dialog.findViewById(R.id.three);
        TextView four = dialog.findViewById(R.id.four);
        TextView six = dialog.findViewById(R.id.six);
        TextView seven = dialog.findViewById(R.id.seven);
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(ten,one,two,three,four,six,seven,dialog.getContext());
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(one, ten,two,three,four,six,seven,dialog.getContext());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(two,ten,one,three,four,six,seven,dialog.getContext());
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(three,two,ten,one,four,six,seven,dialog.getContext());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(four,two,ten,one,three,six,seven,dialog.getContext());
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(six,two,ten,one,three,four,seven,dialog.getContext());
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAble(seven,ten,one,two,three,four,six,dialog.getContext());
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
    //методы для кнопок времени
    public void enableTextView(TextView textView, Context context){
        textView.setTextColor(ContextCompat.getColor(Order.this, R.color.white));
        textView.setBackgroundResource(R.drawable.enabledtextview);
    }
    public void disableTextView(TextView textView, Context context){
        textView.setTextColor(ContextCompat.getColor(context, R.color.hintcolor));
        textView.setBackgroundResource(R.drawable.disabledtextview);
    }
    public void setAble(TextView textView1, TextView textView2, TextView textView3,TextView textView4,TextView textView5,TextView textView6,TextView textView7, Context context){
        enableTextView(textView1,context);
        disableTextView(textView2, context);
        disableTextView(textView3, context);
        disableTextView(textView4, context);
        disableTextView(textView5, context);
        disableTextView(textView6, context);
        disableTextView(textView7, context);
        time = textView1.getText().toString();
    }


    public void Back(View view){
        Intent intent = new Intent(Order.this, ActivityMenu.class);
        startActivity(intent);
    }
}