package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Order extends AppCompatActivity {
    TextView dateshow,patientsList,countanalises,analises,price,btnNext,perem;
    AutoCompleteTextView patientsChoice;
    //PHONE
    EditText phoneEdit;
    PhoneKit phoneKit = new PhoneKit();
    private RecyclerView listAnalises;
    Double totalprice = 0.0;
    boolean editdate,editperson,editphone,btnnextenabled;

    SharedPreferences preferences;
    Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        preferences = getSharedPreferences("UserInfo", 0);
        //НОМЕР ТЕЛЕФОНА
        phoneEdit = findViewById(R.id.phoneEdit);
        analises = findViewById(R.id.analises);
        listAnalises = findViewById(R.id.listAnalises);
        countanalises = findViewById(R.id.countanalises);
        perem = findViewById(R.id.perem);
        Integer count = Integer.valueOf(preferences.getString("countanalises",null));
        countanalises.setText(preferences.getString("countanalises",null));
        String[]analyses = preferences.getString("analises",null).split(";");
        String[]prices = preferences.getString("prices",null).split(";");
        boolean createdcard = preferences.getBoolean("createdcard",false);
        //Изменение текста анализа
        if(count>1&&count<5)analises.setText("анализа");
        else if(count>=5)analises.setText("анализов");
        else analises.setText("анализ");
        price = findViewById(R.id.price);
        //НОМЕР ТЕЛЕФОНА
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phoneKit.phoneKit(editable);
                if(phoneEdit.length()!=0){
                    editphone = true;
                }else editphone = false;
                if(editphone&&editdate&&editperson){
                    btnNext.setBackgroundResource(R.drawable.enabledbutton);
                    btnnextenabled = true;
                }
                else {
                    btnNext.setBackgroundResource(R.drawable.disabledbutton);
                    btnnextenabled = false;
                }
            }
        });
        //ВЫБОР ДАТЫ
        dateshow = findViewById(R.id.dateshow);
        dateshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
        dateshow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(dateshow.length()!=0){
                    editdate = true;
                }else editdate = false;
                if(editphone&&editdate&&editperson){
                    btnNext.setBackgroundResource(R.drawable.enabledbutton);
                    btnnextenabled = true;
                }
                else {
                    btnNext.setBackgroundResource(R.drawable.disabledbutton);
                    btnnextenabled = false;
                }
            }
        });
        //ВЫБОР ПАЦИЕНТА
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
                if(createdcard){
                    patientsList.setText(patientsChoice.getText().toString());
                    String pol = preferences.getString("gender",null);
                    if(pol.equals("Мужской")){
                        patientsList.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.male),null,null,null);
                    }else  patientsList.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.female),null,null,null);
                    if(patientsChoice.length()!=0){
                        editperson = true;
                    }else editperson = false;
                    if(editphone&&editdate&&editperson){
                        btnNext.setBackgroundResource(R.drawable.enabledbutton);
                        btnnextenabled = true;
                    }
                    else {
                        btnNext.setBackgroundResource(R.drawable.disabledbutton);
                        btnnextenabled = false;
                    }
                }else Toast.makeText(getApplicationContext(),"Зарегистрируйтесь",Toast.LENGTH_SHORT).show();
            }
        });
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnnextenabled){
                    Intent intent = new Intent(Order.this, PayingActivity.class);
                    startActivity(intent);
                }else Toast.makeText(Order.this, "Не все поля заполнены", Toast.LENGTH_SHORT).show();
            }
        });
        perem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Double pricedeletedanalys = Double.parseDouble(perem.getText().toString());
                Double priceofanalyses = Double.parseDouble(price.getText().toString());
                priceofanalyses = priceofanalyses - pricedeletedanalys;
                BigInteger pricetotal = BigInteger.valueOf(priceofanalyses.intValue());
                price.setText(pricetotal.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        countanalises.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int count = Integer.parseInt(countanalises.getText().toString());
                if(count<5&&count>1)analises.setText("анализа");
                else if(count>=5||count ==0)analises.setText("анализов");
                else analises.setText("анализ");
                if(count != 0&&editphone&&editdate&&editperson){
                    btnnextenabled=true;
                    btnNext.setBackgroundResource(R.drawable.enabledbutton);
                }else{
                    btnnextenabled = false;
                    btnNext.setBackgroundResource(R.drawable.disabledbutton);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //Заполнение данными выбранных анализов
        List<BasketModel> orderModel = new ArrayList<>();
        RecyclerView.Adapter orderAdapterT = new OrderAdapterT(this,orderModel,perem,countanalises);


        listAnalises.setAdapter(orderAdapterT);
        orderModel.clear();
        for (int i = 0;i<analyses.length;i++){
            if(analyses[i].length()!=0){
                Double price =Double.parseDouble(prices[i]);
                BigInteger price2 = BigInteger.valueOf(price.intValue());
                totalprice+=price;
                orderModel.add(new BasketModel("",analyses[i],price2.toString()));

                orderAdapterT.notifyItemInserted(orderModel.size()-1);

            }
        }
        BigInteger pricetotal = BigInteger.valueOf(totalprice.intValue());
        price.setText(pricetotal.toString());
        orderAdapterT.notifyItemInserted(orderModel.size()-1);

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
                dateshow.setText(daysList.getText() + " " + time);
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