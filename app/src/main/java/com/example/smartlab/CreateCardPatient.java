package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Calendar;

public class CreateCardPatient extends AppCompatActivity {
    SharedPreferences preferences;
    boolean name,patronymic,surname,dateofbirth,genderbool,btnEnabled;
    EnableTextView ETV = new EnableTextView();
    TextView btnCreate,editname,editpatronymic,editsurname,editdateofbirth,gendersList;
    Calendar calendar = Calendar.getInstance();
TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card_patient);
        skip = findViewById(R.id.skip);
        btnCreate = findViewById(R.id.btnCreate);
        gendersList= findViewById(R.id.gendersList);
//ВЫПАДАЮЩИЙ СПИСОК
        AutoCompleteTextView genderChoice = findViewById(R.id.genderChoice);
        String[] gender = {"Мужской","Женский"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_items, gender);
        genderChoice.setAdapter(arrayAdapter);
        genderChoice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                gendersList.setText(genderChoice.getText().toString());
            }
        });

//поля ввода
        editname = findViewById(R.id.editname);
        editpatronymic = findViewById(R.id.editpatronymic);
        editsurname = findViewById(R.id.editsurname);
        editdateofbirth = findViewById(R.id.editdateofbirth);
        preferences = getSharedPreferences("UserInfo", 0);


        editname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editname.getText().toString().length()!=0){
                    name=true;
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnCreate,getApplicationContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnCreate,getApplicationContext());
                    btnEnabled = false;
                }
            }
        });
        editpatronymic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editpatronymic.getText().toString().length()!=0){
                    patronymic=true;
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnCreate,getApplicationContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnCreate,getApplicationContext());
                    btnEnabled = false;
                }
            }
        });
        editsurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editsurname.getText().toString().length()!=0){
                surname=true;
                if(name && patronymic && surname&& dateofbirth && genderbool ){
                    ETV.onEnableBtn(btnCreate,getApplicationContext());
                    btnEnabled = true;
                }
            }
            else {
                ETV.onDisableBtn(btnCreate,getApplicationContext());
                    btnEnabled = false;
                }
            }
        });
        editdateofbirth.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editdateofbirth.getText().toString().length()!=0){
                    dateofbirth=true;
                    editdateofbirth.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnCreate,getApplicationContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnCreate,getApplicationContext());
                    btnEnabled = false;
                }
            }
        });
        gendersList.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(gendersList.getText().toString().length()!=0){
                    genderbool=true;
                    gendersList.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
                    if(name && patronymic && surname&& dateofbirth && genderbool){
                        ETV.onEnableBtn(btnCreate,getApplicationContext());
                        btnEnabled = true;
                    }
                }
                else {
                    ETV.onDisableBtn(btnCreate,getApplicationContext());
                    btnEnabled = false;
                }
            }
        });



    }
    //ДАТА
    public void getdateBtn(View view){
        new DatePickerDialog(CreateCardPatient.this,d,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    // установка начальных дат
    private void setInitialDate() {

        editdateofbirth.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };
    boolean createdcard;
    public void onCreate(View view){
        if(btnEnabled){
            createdcard = true;
            String nameValue = editname.getText().toString();
            String patronymicValue = editpatronymic.getText().toString();
            String surnameValue = editsurname.getText().toString();
            String dateValue = editdateofbirth.getText().toString();
            String genderValue = gendersList.getText().toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", nameValue);
            editor.putString("patronymic", patronymicValue);
            editor.putString("surname",surnameValue);
            editor.putString("date",dateValue);
            editor.putString("gender",genderValue);
            editor.putBoolean("createdcard", createdcard);
            editor.apply();
            Intent intent = new Intent(CreateCardPatient.this,ActivityMenu.class);
            startActivity(intent);
        }
        else  Toast.makeText(getApplicationContext(),"Не все поля заполнены",Toast.LENGTH_SHORT).show();
    }

    public void Skip(View view){
        Intent intent = new Intent(CreateCardPatient.this,ActivityMenu.class);
        startActivity(intent);
    }
}