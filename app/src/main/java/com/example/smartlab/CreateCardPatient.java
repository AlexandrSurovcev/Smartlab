package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCardPatient extends AppCompatActivity {
    SharedPreferences preferences;
    boolean name,patronymic,surname,dateofbirth,btnEnabled;
    EnableTextView ETV = new EnableTextView();
    String[] genders = {"Женский", "Мужской"};
    TextView btnCreate,editname,editpatronymic,editsurname,editdateofbirth;
    Spinner gender;
TextView skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card_patient);
        skip = findViewById(R.id.skip);
        btnCreate = findViewById(R.id.btnCreate);



//спиннер
        gender = findViewById(R.id.editgender);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

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
                    if(name && patronymic && surname){
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
                    if(name && patronymic && surname){
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
                if(name && patronymic && surname ){
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
    public void onCreate(View view){
        if(btnEnabled){
            String nameValue = editname.getText().toString();
            String patronymicValue = editpatronymic.getText().toString();
            String surnameValue = editsurname.getText().toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", nameValue);
            editor.putString("patronymic", patronymicValue);
            editor.putString("surname",surnameValue);
//            editor.putString("dateofbirth",dateValue);
//            editor.putString("gender",true);
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